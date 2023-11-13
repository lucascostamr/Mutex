import java.util.concurrent.Semaphore;

public class Filosofo implements Runnable{
    private int 
        index,
        direito,
        esquerdo;
    
    private static boolean isAvaible = true;
    private static Semaphore[] hashi = new Semaphore[NUMFILO];


    public Filosofo(int numFilosofo, int totalFilosofos){
        this.index = numFilosofo;
        this.direito = numFilosofo;

        this.esquerdo = (index + 1) % totalFilosofos;
        
        for(int i = 0; i < totalFilosofos; i++) {
            //Cria um semaforo para cada Filosofo
            hashi[i] = new Semaphore(1); //Permite apenas um
        }
    }

    @Override
    public void run(){
        meditar();
        while(isAvaible) {
            hashi[this.direito].acquire();
            hashi[this.esquerdo].acquire();
            hashi[this.direito].release();
            hashi[this.esquerdo].release();

        }
        return;
    }
}
