package problemaComSemaphore;

import java.util.Random;

public class Philosopher implements Runnable {
  // The Philosopher identifier
  private int philosopherId;
  private int forks = 0;  
  // A reference to the shared table instance
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

  // The randomized time that each Philosopher spends thinking
  private void think() {
      System.out.println("Philosopher " + philosopherId + " thinking..");
      try {
          Thread.sleep(new Random().nextInt(5000));
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }

  // The eating process of each Philosopher instance
//   private void eat() {
//       int left = philosopherId;
//       int right = (philosopherId + 1) % table.forks.size();
//       left = new Random().nextInt(table.forks.size());  
//       right = new Random().nextInt(table.forks.size());  

//       System.out.println("Philosopher " + philosopherId + " taking forks..");

//       try {
//           // Acquire both forks
//           table.forks.get(left).acquire();
//           table.forks.get(right).acquire();

//           System.out.println("Philosopher " + philosopherId + " eating..");
//           Thread.sleep(new Random().nextInt(10000));
//       } catch (InterruptedException e) {
//           e.printStackTrace();
//       } finally {
//           // Release both forks
//           table.forks.get(left).release();
//           table.forks.get(right).release();
//           System.out.println("Philosopher " + philosopherId + " releasing forks..");
//       }
//   }
  private void eat() {
    int left = philosopherId;
    int right = (philosopherId + 1) % table.forks.size();
    left = new Random().nextInt(table.forks.size());  
    right = new Random().nextInt(table.forks.size());  

    System.out.println("Philosopher " + philosopherId + " taking forks..");

        // Acquire both forks
        table.forks.get(left);
        table.forks.get(right).acquire();

        System.out.println("Philosopher " + philosopherId + " eating..");
        Thread.sleep(new Random().nextInt(10000));
    
        // Release both forks
        table.forks.get(left).release();
        table.forks.get(right).release();
        System.out.println("Philosopher " + philosopherId + " releasing forks..");
    
}

    private void getFork(){
        if( forks >= 0 || forks < 2){

            while(!)
        }

        this.forks--;
    }
    private void putFork(){
        this.forks++;
    }

  

}
