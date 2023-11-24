package problemaComSemaphore;
import java.util.concurrent.Semaphore;
import java.util.ArrayList;
import java.util.List;

public class Table {
    
    // The shared resource that each Philosopher will contend for
    List<Semaphore> forks;

    Table() {
        forks = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            forks.add(new Semaphore(1)); // Each fork is initially available
        }
    }
}