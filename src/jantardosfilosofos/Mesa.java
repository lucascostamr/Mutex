
package jantardosfilosofos;


import javax.swing.JPanel;
import java.awt.*;
import java.util.concurrent.Semaphore;

public class Mesa extends JPanel implements Runnable {
    final int PENSANDO = 0;
    final int FAMINTO = 1;
    final int COMENDO = 2;

    String mensagem = "";
    Thread animador;
    
    // Substituição da classe Semaforo por Semaphore
    public static Semaphore saleiro = new Semaphore(1);
    public static Semaphore[] garfo = new Semaphore[5];
    public static int[] estado = new int[5];
    public static Filosofo[] filosofo = new Filosofo[5];

    public Mesa() {
        setFocusable(true);
        setSize(400, 400);
        setBackground(Color.white);
        init();
    }

    public void init() {
        for (int i = 0; i < estado.length; i++) {
            estado[i] = 0;
        }

        if (animador == null) {
            animador = new Thread(this);
            animador.start();
        }

        Thread.currentThread().setPriority(1);

        filosofo[0] = new Filosofo("Platao", 0);
        filosofo[1] = new Filosofo("Socrates", 1);
        filosofo[2] = new Filosofo("Aristoteles", 2);
        filosofo[3] = new Filosofo("Tales", 3);
        filosofo[4] = new Filosofo("Sofocles", 4);

        for (int i = 0; i < 5; i++) {
            garfo[i] = new Semaphore(1);
            filosofo[i].start();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.blue);
        g.drawOval(50, 50, 300, 300);

        for (int i = 0; i < 5; i++) {
            if (estado[i] == 0) {
                g.setColor(Color.gray);
                mensagem = "PENSANDO";
            }
            if (estado[i] == 1) {
                g.setColor(Color.yellow);
                mensagem = "FAMINTO";
            }
            if (estado[i] == 2) {
                g.setColor(Color.green);
                mensagem = "COMENDO";
            }

            g.fillOval((int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 15,
                    (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) - 15, 30, 30);
            g.setColor(Color.black);
            g.drawLine((int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 5,
                    (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) + 5,
                    (int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) + 5,
                    (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) + 5);
            g.drawLine((int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 2,
                    (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) - 3,
                    (int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) + 2,
                    (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)));
            g.drawLine((int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 2,
                    (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)),
                    (int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) + 2,
                    (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)));
            g.drawLine((int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 8,
                    (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) - 8,
                    (int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 3,
                    (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) - 8);
            g.drawLine((int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) + 3,
                    (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) - 8,
                    (int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) + 8,
                    (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) - 8);
            g.drawString(filosofo[i].getName(),
                    (int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 15,
                    (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) + 25);
            g.drawString(mensagem, (int) (200D - 100D * Math.cos(1.2566370614359172D * (double) i)) - 15,
                    (int) (200D - 100D * Math.sin(1.2566370614359172D * (double) i)) + 40);
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    @Override
    public void run() {
        do {
            repaint();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ex) {
                System.out.println("ERROR>" + ex.getMessage());
            }
        } while (true);
    }
}
