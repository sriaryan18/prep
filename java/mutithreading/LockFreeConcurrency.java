import java.util.concurrent.atomic.AtomicInteger;

public class LockFreeConcurrency {
    
    AtomicInteger integer = new AtomicInteger(0);

    public void increment(){
        integer.incrementAndGet();
    }

    public int get(){
       return integer.get();
    }


}
