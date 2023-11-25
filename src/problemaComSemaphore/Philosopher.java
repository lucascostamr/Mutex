package problemaComSemaphore;

import java.util.Random;

public class Philosopher implements Runnable {
  // The Philosopher identifier
  private int philosopherId;
  private int forksNaMao = 0;  
  private int naoComi = 0;
  
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
          eat2();
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

  //The eating process of each Philosopher instance
  private void eat() {
      int left = philosopherId;
      int right = (philosopherId + 1) % table.forks.size();
      left = new Random().nextInt(table.forks.size());  
      right = new Random().nextInt(table.forks.size());  

      System.out.println("Philosopher " + philosopherId + " taking forks..");

      try {
          // Acquire both forks
          table.forks.get(left).acquire();
          table.forks.get(right).acquire();

          System.out.println("Philosopher " + philosopherId + " eating..");
          Thread.sleep(new Random().nextInt(10000));
      } catch (InterruptedException e) {
          e.printStackTrace();
      } finally {
          // Release both forks
          table.forks.get(left).release();
          table.forks.get(right).release();
          System.out.println("Philosopher " + philosopherId + " releasing forks..");
      }
  }
//   private void eat() {
//     int left = philosopherId;
//     int right = (philosopherId + 1) % table.forks.size();
//     left = new Random().nextInt(table.forks.size());  
//     right = new Random().nextInt(table.forks.size());  

//     System.out.println("Philosopher " + philosopherId + " taking forks..");

//         // Acquire both forks
//         table.forks.get(left);
//         table.forks.get(right).acquire();

//         System.out.println("Philosopher " + philosopherId + " eating..");
//         Thread.sleep(new Random().nextInt(10000));
    
//         // Release both forks
//         table.forks.get(left).release();
//         table.forks.get(right).release();
//         System.out.println("Philosopher " + philosopherId + " releasing forks..");
    
// }

    private void eat2(){


        while(forksNaMao >=0 && forksNaMao < 2){
            if(table.getTotalForks() > 0){
                  table.takeFork();  
                  //System.out.println(philosopherId+ "pegando um garfo");
                  this.forksNaMao++;
            }
            if(table.getTotalForks() > 0){
                  table.takeFork();  
                  //System.out.println(philosopherId+ "pegando o segundo garfo");
                  this.forksNaMao++;
            }
           // System.out.println(forksNaMao);
            if(forksNaMao == 1){
                System.out.println("nao comi");
                this.naoComi++;
                table.putFork();
                this.forksNaMao--;
            }
        }
        
        System.out.println("comendo" + naoComi);
        table.putFork();
        this.forksNaMao--;
        table.putFork();
        this.forksNaMao--;

    }

    private void eat3(){


        while(forksNaMao >=0 && forksNaMao < 2){
            if(table.getTotalForks() > 0){
                  table.takeFork();  
                  //System.out.println(philosopherId+ "pegando um garfo");
                  this.forksNaMao++;
            }

        }
        System.out.println("comendo" + naoComi);
        table.putFork();
        this.forksNaMao--;
        table.putFork();
        this.forksNaMao--;

    }

  

}
