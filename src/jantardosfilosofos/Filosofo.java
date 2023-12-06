package jantardosfilosofos;

import java.util.Random;

public class Filosofo extends Thread {
    private int ID;

    final int PENSANDO = 0;
    final int FAMINTO = 1;
    final int COMENDO = 2;

    public Filosofo(String nome, int ID) {
        super(nome);
        this.ID = ID;
    }

    public void ComFome() {
        Mesa.estado[this.ID] = 1;
        System.out.println("O Filósofo " + getName() + " está FAMINTO!");
    }

    public void Come() {
        Mesa.estado[this.ID] = 2;
        System.out.println("O Filósofo " + getName() + " está COMENDO!");

        try {
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException ex) {
            System.out.println("ERROR>" + ex.getMessage());
        }
    }

    public void Pensa() {
        Mesa.estado[this.ID] = 0;
        System.out.println("O Filósofo " + getName() + " está PENSANDO!");
        try {
            Thread.sleep(new Random().nextInt(10));
        } catch (InterruptedException ex) {
            System.out.println("ERROR>" + ex.getMessage());
        }
    }

    public void LargarGarfo() throws InterruptedException {
        Mesa.garfo[this.ID].release();
        Pensa();
        Mesa.filosofo[VizinhoEsquerda()].TentarObterGarfos();
        Mesa.filosofo[VizinhoDireita()].TentarObterGarfos();
    }
    
    public void PegarGarfo() {
        try {
            //Mesa.saleiro.acquire(); 
            ComFome();
            TentarObterGarfos();

        } catch (InterruptedException ex) {
            System.out.println("ERROR>" + ex.getMessage());
        } finally {
            //Mesa.saleiro.release();
            Mesa.garfo[this.ID].release();
            if(Mesa.estado[this.ID] == 1){
                Mesa.estado[this.ID] = 3;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("ERROR>" + ex.getMessage());
                }
                Mesa.estado[this.ID] = 1;
            }
        }
    }
    public void TentarObterGarfos() throws InterruptedException {
        if (Mesa.estado[this.ID] == 1 &&
            Mesa.estado[VizinhoEsquerda()] != 2 &&
            Mesa.estado[VizinhoDireita()] != 2) {   
            Come();
            Mesa.garfo[this.ID].release();
        }
    }


    

    @Override
    public void run() {
        try {
            Pensa();
            do {
                PegarGarfo();
                Thread.sleep(1000L);
                LargarGarfo();
            } while (true);
        } catch (InterruptedException ex) {
            System.out.println("ERROR>" + ex.getMessage());
            return;
        }
    }


    public int VizinhoDireita() {
        return (this.ID + 1) % 5;
    }

    public int VizinhoEsquerda() {
        return (this.ID - 1 + 5) % 5; 
    }
}
