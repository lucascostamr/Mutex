package problemaComSemaphore;

import java.util.Random;

public class Filosofo implements Runnable {
    private int filosofoId;
    private Mesa mesa;

    Filosofo(int id, Mesa mesa) {
        filosofoId = id;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        while (true) {
            pensa();
            comeSolucaoPerfeita();
        }
    }

    private void pensa() {
        System.out.println("Filósofo " + filosofoId + " pensando..");
        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void comeSolucaoPerfeita() {
        int garfoEsquerda = filosofoId;
        int garfoDireita = (filosofoId + 1) % mesa.garfos.size();

        System.out.println("Filósofo " + filosofoId + " pegando garfos..");

        try {
            mesa.garfos.get(garfoEsquerda).acquire();
            mesa.garfos.get(garfoDireita).acquire();

            mesa.setEstado(filosofoId, 2); // Filósofo está comendo
            System.out.println("Filósofo " + filosofoId + " comendo..");

            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mesa.garfos.get(garfoEsquerda).release();
            mesa.garfos.get(garfoDireita).release();
            mesa.setEstado(filosofoId, 0); // Filósofo está pensando após comer
        }
    }
}
