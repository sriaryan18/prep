public class ThreadSafeCounter {

    public int counter = 0;

    public synchronized void increment() {
        try {
            System.out.println("Incrementing the thread " + Thread.currentThread().getName());
            Thread.sleep(2000);
            ++counter;
            System.out.println("COUNTER VALUE : " + counter);
        } catch (Exception ex) {

        }
    }

    public synchronized void decrement() {
        try {
            System.out.println("Decrementing the thread " + Thread.currentThread().getName());
            Thread.sleep(2000);
            --counter;
            System.out.println("COUNTER VALUE : " + counter);

        } catch (Exception ex) {

        }
    }
}
