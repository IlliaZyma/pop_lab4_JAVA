import java.util.concurrent.Semaphore;
public class Main {
        public static void main(String[] args) {
            Phylosopher[] philosophers = new Phylosopher[5];
            for (int i = 0; i < 5; i++) {
                philosophers[i] = new Phylosopher(i);
                philosophers[i].start();
            }
        }
}