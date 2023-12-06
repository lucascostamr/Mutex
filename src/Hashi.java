import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class Hashi {
    private static Semaphore[] hashi;
    private static String[] status = {"LIVRE","LIVRE","LIVRE","LIVRE","LIVRE"};

    public Hashi(int qntFilosofos) {
        hashi = new Semaphore[qntFilosofos];

        for(int i = 0; i < qntFilosofos; i++) {
            hashi[i] = new Semaphore(1);
        }
    }

    public void showStatus() {
        System.out.println("\nStatus Hashi:\n" + Arrays.toString(status));
    }

    public Semaphore[] getHashi() {
        return hashi;
    }

    public String[] getStatus() {
        return status;
    }
}
