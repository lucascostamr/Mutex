package problemaComSemaphore;

import java.util.Arrays;
import java.util.Random;

public class Philosopher implements Runnable {
  // The Philosopher identifier
  private int philosopherId;
  private int forksInHand = 0;  
  private int notEat = 0;
  private boolean leftFork = false;
  private boolean rightFork = false;
  private boolean eating = false;
  private boolean thinking = false;

  
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
          eatPerfectSolution();
      }
  }

  // The randomized time that each Philosopher spends thinking
  private void think() {
      thinking = !thinking;
      System.out.println("Philosopher " + philosopherId + " thinking..");
      try {
          Thread.sleep(new Random().nextInt(5000));
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      thinking = !thinking;
  }

  //The eating process of each Philosopher instance
  private void eatPerfectSolution() {
      int left = philosopherId;
      int right = (philosopherId + 1) % table.forks.size();


      System.out.println("Philosopher " + philosopherId + " taking forks..");

      try {
          // Acquire both forks
          table.forks.get(left).acquire();
          leftFork = !leftFork;
          table.forks.get(right).acquire();
          rightFork = !rightFork;

          eating = !eating;
          Thread.sleep(new Random().nextInt(10000));
          eating = !eating;
      } catch (InterruptedException e) {
          e.printStackTrace();
      } finally {
          
        // Release both forks
        table.forks.get(left).release();
        leftFork = !leftFork;
        table.forks.get(right).release();
        rightFork = !rightFork;
      }
  }


    private void eat2(){


        while(forksInHand >=0 && forksInHand < 2){
            if(table.getTotalForks() > 0){
                  table.takeFork();  
                  System.out.println(philosopherId+ "pegando um garfo");
                  this.forksInHand++;
            }
            if(table.getTotalForks() > 0){
                  table.takeFork();  
                  System.out.println(philosopherId+ "pegando o segundo garfo");
                  this.forksInHand++;
            }
           // System.out.println(forksNaMao);
            if(forksInHand == 1){
                System.out.println("nao comi");
                this.notEat++;
                table.putFork();
                this.forksInHand--;
            }
        }
        
        System.out.println("comendo" + notEat);
        table.putFork();
        this.forksInHand--;
        table.putFork();
        this.forksInHand--;

    }

    private void eatSimpleSolutionNoMutex(){
        while(forksInHand >=0 && forksInHand < 2){
            if(table.getTotalForks() > 0){
                  table.takeFork();  
                  System.out.println(philosopherId+ "pegando um garfo");
                  this.forksInHand++;
            }
        }
        System.out.println("comendo" + notEat);
        table.putFork();
        this.forksInHand--;
        table.putFork();
        this.forksInHand--;
    }

    @Override
    public String toString() {
        return "Philosopher{" +
                "philosopherId=" + philosopherId +
                ", leftFork=" + leftFork +
                ", rightFork=" + rightFork +
                ", eating=" + eating +
                ", thinking=" + thinking +
                '}';
    }
    
}
