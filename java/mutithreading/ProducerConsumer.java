
import java.util.ArrayList;
import java.util.List;

public class ProducerConsumer {
    List<String> queue = new ArrayList<>();

    public synchronized void produce(){
        try{
            Thread.sleep(2000);
            if(queue.size() > 3){
                System.out.println("Waiting because queue is full "+  Thread.currentThread().getName());
                wait();
            }
            System.out.println("Coming out in produce "+  Thread.currentThread().getName());
            queue.add(Thread.currentThread().getName());
            notifyAll();
        }catch(Exception e){

        }
    }

    public synchronized void consume(){
        try{
            Thread.sleep(2000);

            if(queue.size() == 0){
                System.out.println("Wating because queue is empty "+   Thread.currentThread().getName());
                wait();
            }
            System.out.println("Coming out in consume "+  Thread.currentThread().getName());

            queue.remove(0);
            notifyAll();
        }catch(Exception ex){

        }
    }

}
