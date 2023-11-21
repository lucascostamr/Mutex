/*
    [] Criar um limite de tempo para o filosofo/chines comer
        [] Caso coma menos que o esperado mostrar que estar com fome
        [] Caso nao como nada morre de fome
    [] Mostrar quantos filosofos estao comendo/pensando
        [] printar no console quais hashis estao ocupados
        [] printar no cosnole quais filosofos estao meditando
        [] printar no console quais filosofos estao com fome, mais de um dia sem comer
        [] mostrar filsofos que solicitaram o hashi no momento
    [] parar quando um dos filosofos morrer de fome
    [] Caso um filoso passe mais de um dia comendo
        [] Interromper
        [] Printar menssagem. "Filosofo x comeu demasiadamente"
*/



import java.util.List;
import java.util.Stack;
import java.util.concurrent.Semaphore;

public class App {
    private static final int NUMFILO = 5;
    private static Semaphore[] hashi = new Semaphore[NUMFILO];
    private static Semaphore saleiro = new Semaphore(1);
    private static List<String> quemMeditou = new Stack<>();
    private static List<String> quemComeu = new Stack<>();
    private static boolean isOn = true; 

    public static void main(String[] args) {
        for (int i = 0; i < NUMFILO; i++) {
            hashi[i] = new Semaphore(1);
        }

        for (int i = 0; i < NUMFILO; i++) {
            new Thread(new Filosofo(i)).start();
        }

        System.out.println(quemMeditou.toString());
        System.out.println(quemComeu.toString());
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
            while (isOn) {
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
            String nomeThread = Thread.currentThread().getName();

            if (quemMeditou.size() == NUMFILO) {
                isOn = false;
                return;
            }

            if(!quemMeditou.contains(nomeThread)) {
                quemMeditou.add(nomeThread);
            }
        }

        private void comer() {
            String nomeThread = Thread.currentThread().getName();

            if (quemComeu.size() == NUMFILO) {
                isOn = false;
                return;
            }

            if(!quemComeu.contains(nomeThread)){
                quemComeu.add(nomeThread);
            }
        }
    }
}