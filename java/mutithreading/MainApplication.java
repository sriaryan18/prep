import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.StructuredTaskScope.Subtask;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainApplication {
    public static void main(String[] args) {
        System.out.println("Hello World from " + Thread.currentThread().getName());

        // One method of running thread.
        // MultiThreadingClass multiThreadingClass = new MultiThreadingClass();
        // Thread t1 = new Thread(multiThreadingClass);
        // t1.setName("ARYAN");
        // t1.start();

        // Second method
        // Thread t1 = new Thread(() ->
        // System.out.println(Thread.currentThread().getName() + " is running"));
        // t1.setName("ARYAN");
        // t1.start();

        // Multiple threads
        // Thread t1 = new Thread(multiThreadingClass);
        // Thread t2 = new Thread(multiThreadingClass);

        // t1.start();
        // t2.start();

        // Locking
        // ProducerConsumer producerConsumer = new ProducerConsumer();

        // for(int i = 0 ;i<10;i++){

        // Thread t2 = new Thread(() -> producerConsumer.consume());
        // Thread t3 = new Thread(() -> producerConsumer.consume());

        // Thread t1 = new Thread(() -> producerConsumer.produce());
        // t1.start();
        // t3.start();
        // t2.start();

        // }

        // Reentract lock
        // SharedResources sharedResources = new SharedResources();
        // Thread t1 = new Thread(() -> sharedResources.produce());
        // Thread t2 = new Thread(() -> sharedResources.produce());
        // t1.setName("T1");
        // t1.start();
        // t2.setName("T2");
        // t2.start();

        // Read write lock
        // ReadWriteLockExample obj = new ReadWriteLockExample();
        // ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        // Thread t1 = new Thread(() ->obj.produce(readWriteLock));
        // Thread t2 = new Thread(() -> obj.produce(readWriteLock));
        // Thread t3 = new Thread(() -> obj.consume(readWriteLock));
        // Thread t4 = new Thread(() -> obj.consume(readWriteLock));

        // t1.start();
        // t2.start();
        // t3.start();
        // t4.start();

        // Stamped lock (optimistic locking)

        // StampedLockExample example = new StampedLockExample();
        // Thread t1 = new Thread(() -> example.produce());
        // Thread t2 = new Thread(() -> example.consume());

        // t1.start();
        // t2.start();

        // CAS (compare and swap) a lock free mechanic to solve concurrency
        // LockFreeConcurrency obj = new LockFreeConcurrency();

        // Thread t1 = new Thread(() -> {
        // for(int i=0 ;i<200 ;i++){
        // obj.increment();
        // }
        // });
        // Thread t2 = new Thread(() -> {
        // for(int i=0 ;i<200; i++){
        // // System.out.println("incrementing in t2");
        // obj.increment();
        // }
        // });

        // System.out.println("Before " + obj.get());
        // t1.start();
        // t2.start();
        // try{

        // t1.join();
        // t2.join();
        // }catch(Exception e){}
        // System.out.println("After " + obj.get());

        // Thread pool executor

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 4, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new CustomThreadFactory(), new CustomRejectHandler());

        // for(int i =0;i<25;i++){
        // threadPoolExecutor.submit(() -> {
        // try{
        // Thread.sleep(5000);
        // System.out.println("Running "+ getThreadName());

        // }catch(Exception ex){}
        // });
        // }

        // Future<?> future = threadPoolExecutor.submit(() -> {
        //     try {
        //         Thread.sleep(1000);
        //         System.out.println(getThreadName());
        //     } catch (Exception e) {
        //     }
        // });

        // try{
        // future.get(2,TimeUnit.SECONDS);
        // }catch(Exception ex){}

        // System.out.println("Is done: "+ future.isDone());

        // try {
        //     Object obj = future.get();
        //     System.out.println(obj);

        // } catch (Exception ex) {
        // }

        // callable

        List<Integer> list = new ArrayList<>();
        Future<List<Integer>> res = threadPoolExecutor.submit(() -> {
            list.add(100);
            System.out.println(getThreadName());

        }, list);

        // try {
        //     List<Integer> l = res.get();
        //     System.out.println(l);
        // } catch (Exception ex) {
        // }

        // Future<List<Integer>> res2 = threadPoolExecutor.submit(() -> {
        //     list.add(100);
        //     System.out.println(getThreadName());

        // }, list);
        // try {
        //     List<Integer> l = res2.get();
        //     System.out.println(l);
        // } catch (Exception ex) {
        // }


        // CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
        //     return "Aryan";
        // },threadPoolExecutor);
        // completableFuture.thenApply((String res) -> {
        //     return res + " Shrivastava";
        // });
        // try{

        //     System.out.println(completableFuture.get());
        // }catch(Exception ex){}

        // // threadPoolExecutor.submit(null)

        // threadPoolExecutor.shutdown();



        int[] arr = new int[1000000];
        for(int i=0;i<1000000;i++){
            arr[i] = i;
        }

        long startTime = System.currentTimeMillis();
        ForkJoinPool pool = ForkJoinPool.commonPool();
        
        

        SumTask task = new SumTask(0,arr.length,arr);

        int result = pool.invoke(task);
        System.out.println("Total sum: " + result);

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");



        int sum = 0;
        for(int i=0;i<1000000;i++){
            sum += i;
        }
        System.out.println("Total sum: " + sum);
        long endTime2 = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime2 - startTime) + "ms");

    }

    public static String getThreadName() {
        return Thread.currentThread().getName();
    }

}

class CustomThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        // TODO Auto-generated method stub
        Thread th = new Thread(r);
        return th;
    }

}

class CustomRejectHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        System.out.println("Rejecting the task due to overload " + r.toString());

    }
}