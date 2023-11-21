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

public class Mesa {
    private static final int NUMFILO = 5;
    private static final int MAXDIAS = 3;

    private static Semaphore[] hashi = new Semaphore[NUMFILO];

    public static void main(String[] args) {
        Filosofo.setHashi(hashi);
        Filosofo.setNUMFILO(NUMFILO);
        
        for (int i = 0; i < NUMFILO; i++) {
            hashi[i] = new Semaphore(1);
        }

        for (int i = 0; i < NUMFILO; i++) {
            new Thread(new Filosofo(i)).start();
        }

        //System.out.println(quemMeditou.toString());
        // System.out.println(quemComeu.toString());
    }
}