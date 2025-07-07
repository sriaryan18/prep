import java.util.concurrent.locks.ReentrantLock;

public class SharedResources {

    boolean isAvailable = false;

    ReentrantLock lock = new ReentrantLock();


    public void test(){
        try{
            lock.lock();
            System.out.println("Is availble is " + isAvailable + " " + MainApplication.getThreadName());
            lock.unlock();
        }catch(Exception e){

        }
    }

    public void produce() {
        try {
            lock.lock();
            System.out.println("Lock accquired by " + MainApplication.getThreadName());
            isAvailable = true;
            Thread.sleep(4000);
            test();
        } catch (Exception e) {

        } finally {
            lock.unlock();
            System.out.println("Lock release by " + MainApplication.getThreadName());
        }
    }

}