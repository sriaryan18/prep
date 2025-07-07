
import java.util.concurrent.locks.StampedLock;

public class StampedLockExample {
    int a = 10;

    StampedLock lock = new StampedLock();


    public void produce(){
        long stamp = lock.tryOptimisticRead();
        try{
            System.out.println("Taken optimistic lock " + MainApplication.getThreadName());
            a = 11;
            Thread.sleep(1000);
            if(lock.validate(stamp)){
                System.out.println("updated a value successfully");
            }else{
                produce();
                System.out.println("Fallback");
                a = 10;
            }


        }catch(Exception e){}
    }


    public void consume(){
        long stamp = lock.writeLock();

        System.out.println("Write lock accquired by " + MainApplication.getThreadName());
        try{
            System.out.println("performing work");
            a = 9;
        }catch(Exception e){}
        finally{
            lock.unlockWrite(stamp);
            System.out.println("Write lock released by "+ MainApplication.getThreadName());
        }
    }
}
