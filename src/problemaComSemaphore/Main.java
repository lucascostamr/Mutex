package problemaComSemaphore;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("THE PHILOSOPHERS DINNER\n");

        Table table = new Table();
        List<Thread> threads = new ArrayList<>(5);

        for (int i = 0; i < 5; i++) {
            Philosopher philosopher = new Philosopher(i, table);
            Thread thread = new Thread(philosopher);
            threads.add(thread);
            thread.start();
        }

        for (Thread filo : threads){
            System.out.println(filo.toString());
        }


        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}