import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class DiningPhilosophers {
    final static int N = 5; // Number of philosophers

    final static int THINKING = 0;
    final static int HUNGRY = 1;
    final static int EATING = 2;

    static int[] state = new int[N]; // Array to control the state of each philosopher
    static Semaphore mutex = new Semaphore(1); // Mutex for critical regions
    static Semaphore[] s = new Semaphore[N]; // One semaphore per philosopher
    static int cont = 10;

    public static void main(String[] args) {
        for (int i = 0; i < N; i++) {
            s[i] = new Semaphore(0); // Initializing semaphores for each philosopher
        }

        // Creating threads for each philosopher
        for (int i = 0; i < N; i++) {
            final int index = i;
            Thread philosopherThread = new Thread(() -> philosopher(index));
            philosopherThread.start();
        }
    }

    static void philosopher(int i) {
        while (true) {
            think(i); // Philosopher is thinking
            takeForks(i); // Philosopher tries to take two forks or blocks
            eat(); // Philosopher is eating
            putForks(i); // Philosopher puts the forks back on the table
        }
    }

    static void takeForks(int i) {
        try {
            mutex.acquire(); // Entering critical region
            state[i] = HUNGRY; // Philosopher is hungry
            test(i); // Trying to pick up two forks
            mutex.release(); // Exiting critical region

            s[i].acquire(); // Blocking if forks were not picked up

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Arrays.toString(state));
        }
    }

    static void putForks(int i) {
        try {
            mutex.acquire(); // Entering critical region
            state[i] = THINKING; // Philosopher finished eating
            test((i + N - 1) % N); // Checking if left neighbor can eat now
            test((i + 1) % N); // Checking if right neighbor can eat now
            mutex.release(); // Exiting critical region
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            
        }
    }

    static void test(int i) {
        if (state[i] == HUNGRY && state[(i + N - 1) % N] != EATING && state[(i + 1) % N] != EATING) {
            state[i] = EATING;
            s[i].release();
        }
    }

    static void think(int i) {
        // Philosopher is thinking
        state[i] = THINKING;
        // System.out.println(Thread.currentThread().getName() + " pensando");
    }

    static void eat() {
        // Philosopher is eating
        System.out.println(Arrays.toString(state));

    }
}

