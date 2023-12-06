package problemaComSemaphore;

import java.util.Random;

public class Philosopher implements Runnable {
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
            eatPerfectSolution();
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

    private void eatPerfectSolution() {
        int left = philosopherId;
        int right = (philosopherId + 1) % table.forks.size();

        System.out.println("Philosopher " + philosopherId + " taking forks..");

        try {
            table.forks.get(left).acquire();
            table.forks.get(right).acquire();

            Thread.sleep(new Random().nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            table.forks.get(left).release();
            table.forks.get(right).release();
        }
    }

}