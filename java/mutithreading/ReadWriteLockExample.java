

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

public class ReadWriteLockExample {
    boolean isAvailable = false;




    public void produce(ReadWriteLock lock) {
        try {
            lock.writeLock().lock();;
            System.out.println("Write lock accquired by " + MainApplication.getThreadName());
            isAvailable = true;
            Thread.sleep(4000);
        } catch (Exception e) {

        } finally {
            lock.writeLock().unlock();
            System.out.println("Lock release by " + MainApplication.getThreadName());
        }
    }

    public void consume(ReadWriteLock lock){

        try{
            lock.readLock().lock();
            System.out.println("Read lock acquired " + MainApplication.getThreadName());
            isAvailable = false;
            Thread.sleep(4000);
        }catch(Exception e){}
        finally{
            lock.readLock().unlock();
            System.out.println("Lock release by " + MainApplication.getThreadName());
        }

    }
}
