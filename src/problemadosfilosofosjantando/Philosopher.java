package problemadosfilosofosjantando;
import java.util.Random;


class Philosopher implements Runnable{
    private int philosopherId;
    
    private Table table;

    Philosopher(int id, Table table) {
        philosopherId = id;
        this.table = table;
    }

    @Override
    public void run() {
        while (true) {
            think();
            eat();
        }
    }

    
    private void think() {
        System.out.println("Philosopher " + philosopherId + " thinking..");
        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void eat() {
        int left = philosopherId;
        int right = (philosopherId + 1) % table.forks.size();

        System.out.println("Philosopher " + philosopherId + " taking forks..");

        // Lock both forks without any deadlock
        table.forks.get(left).lock();
        table.forks.get(right).lock();

        try {
            System.out.println("Philosopher " + philosopherId + " eating..");
            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            
            table.forks.get(left).unlock();
            table.forks.get(right).unlock();
            System.out.println("Philosopher " + philosopherId + " releasing forks..");
        }
    }

    
}