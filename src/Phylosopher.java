import java.util.concurrent.Semaphore;

public class Phylosopher extends Thread{
    int id;
    static Semaphore[] forks = {new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1), new Semaphore(1)};
    Phylosopher(int id) {
        this.id = id;
    }
    @Override
    public void run() {
        int leftFork = id;
        int rightFork = (id + 1) % 5;
        try {
            for (int i = 1; i <= 10; i++) {
                System.out.println("Philosopher " + (id+1) + " thinking " + i + " time");
                if(id % 2 == 0){
                    forks[rightFork].acquire();
                    System.out.println("Philosopher " + id + " took right fork");
                    forks[leftFork].acquire();
                    System.out.println("Philosopher " + id + " took left fork");
                }
                else {
                    forks[leftFork].acquire();
                    System.out.println("Philosopher " + id + " took left fork");
                    forks[rightFork].acquire();
                    System.out.println("Philosopher " + id + " took right fork");
                }

                System.out.println("Philosopher " + id + " eating " + i + " time");
                sleep(1000);
                forks[rightFork].release();
                System.out.println("Philosopher " + id + " put right fork");

                forks[leftFork].release();
                System.out.println("Philosopher " + id + " put left fork");
            }
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }
