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

        for (int i = 1; i <= 10; i++) {
            System.out.println("Philosopher " + (id+1) + " thinking " + i + " time");

            if (forks[leftFork].availablePermits() == 1 && forks[rightFork].availablePermits() == 1) {
                try {
                    forks[leftFork].acquire();
                    System.out.println("Philosopher " + id + " took left fork");

                    forks[rightFork].acquire();
                    System.out.println("Philosopher " + id + " took right fork");

                    System.out.println("Philosopher " + id + " eating " + i + " time");

                    forks[rightFork].release();
                    System.out.println("Philosopher " + id + " put right fork");

                    forks[leftFork].release();
                    System.out.println("Philosopher " + id + " put left fork");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                while (true) {
                    if (forks[leftFork].availablePermits() != 1 && forks[rightFork].availablePermits() != 1) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        break;}
                }

            }
        }
    }

}
