import java.util.concurrent.Semaphore;

public class App {
    private static final int NUMFILO = 5;
    // private static Semaphore[] hashi = new Semaphore[NUMFILO];
    private static Semaphore saleiro = new Semaphore(1);

    public static void main(String[] args) {
        Hashi hashi = new Hashi(NUMFILO);

        for (int i = 0; i < NUMFILO; i++) {
            new Thread(new Filosofo(i, hashi)).start();
        }
    }

    static class Filosofo implements Runnable {
        private int i;
        private int dir;
        private int esq;
        private int cont = 1;
        private Hashi hashi;
        private Semaphore[] hashiSemaphores;

        public Filosofo(int i, Hashi hashi) {
            this.i = i;
            this.dir = i;
            this.esq = (i + 1) % NUMFILO;
            this.hashi = hashi;
            this.hashiSemaphores = hashi.getHashi();
        }

        @Override
        public void run() {
            while (true) {
                meditar();
                try {
                    saleiro.acquire(); // Com o saleiro todos conseguem se alimentar. Se retirar o saleiro entra em deadlock causando starvetion

                    hashiSemaphores[dir].acquire(); // pega palito direito
                    hashi.getStatus()[dir] = "OCUPADO";
                    hashi.showStatus();

                    hashiSemaphores[esq].acquire(); // pega palito esquerdo
                    hashi.getStatus()[esq] = "OCUPADO";
                    hashi.showStatus();

                    saleiro.release();
                    
                    comer();

                    hashiSemaphores[dir].release(); // devolve palito direito
                    hashi.getStatus()[dir] = "LIVRE";
                    hashi.showStatus();

                    hashiSemaphores[esq].release(); // devolve palito esquerdo
                    hashi.getStatus()[esq] = "LIVRE";
                    hashi.showStatus();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    cont--;
                }
                hashi.showStatus();
            }
        }

        private void meditar() {
            try {
                System.out.println(Thread.currentThread().getName());
                System.out.println("meditando");

                Thread.sleep(3000);

            } catch (Exception e) {
                System.err.println(e);
            }
        }

        private void comer() {
            // implementação da função comer
            try {
                System.out.println("\n" + Thread.currentThread().getName());
                System.out.println("comendo");
                Thread.sleep(3000);
            } catch (Exception e) {
                System.err.println(e);
            }

        }
    }
}