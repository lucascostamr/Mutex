package problemadosfilosofosjantando;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Table {
    
    List<Lock> forks;

    Table() {
        forks = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            forks.add(new ReentrantLock());
        }
    }
} 