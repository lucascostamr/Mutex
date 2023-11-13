import java.util.concurrent.Semaphore;

public class App {
   private static final int NUMFILO = 2;
   private static Semaphore[] hashi = new Semaphore[NUMFILO];

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
                  hashi[dir].acquire(); // pega palito direito
                  hashi[esq].acquire(); // pega palito esquerdo
                  comer();
                  hashi[dir].release(); // devolve palito direito
                  hashi[esq].release(); // devolve palito esquerdo
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
           }
       }

       private void meditar() {
           // implementação da função meditar
            System.out.println(Thread.currentThread().getName());

           System.out.println("meditando");
       }

       private void comer() {
           // implementação da função comer
           System.out.println("comendo");
       }
   }
}