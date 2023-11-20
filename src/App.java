import java.util.concurrent.Semaphore;

public class App {
    private static final int NUMFILO = 5;
    private static Semaphore[] hashi = new Semaphore[NUMFILO];
    private static Semaphore saleiro = new Semaphore(1);

    public static void main(String[] args) {
        for (int i = 0; i < NUMFILO; i++) {
            hashi[i] = new Semaphore(1);
        }

        for (int i = 0; i < NUMFILO; i++) {
            new Thread(new Filosofo(i)).start();
        }
    }

    static class Filosofo implements Runnable {
        private int i;
        private int dir;
        private int esq;

        public Filosofo(int i) {
            this.i = i;
            this.dir = i;
            this.esq = (i + 1) % NUMFILO;
        }

        @Override
        public void run() {
            while (true) {
                meditar();
                try {
                    saleiro.acquire(); // Com o saleiro todos conseguem se alimentar. Se retirar o saleiro entra em deadlock causando starvetion
                    hashi[dir].acquire(); // pega palito direito
                    hashi[esq].acquire(); // pega palito esquerdo
                    saleiro.release();
                    comer();
                    hashi[dir].release(); // devolve palito direito
                    hashi[esq].release(); // devolve palito esquerdo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void meditar() {
            //Comentei os prints para deixar a sainda mais limpa. Vamo pensar em alguma coisa pra colocar aqui. Um contador talvez

            // implementação da função meditar
            // System.out.println(Thread.currentThread().getName());
            // System.out.println("meditando");
        }

        private void comer() {
            // implementação da função comer
            System.out.println("\n" + Thread.currentThread().getName());
            System.out.println("comendo");
        }
    }
}