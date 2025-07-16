import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class MainApplication {
    public static void main(String[] args) {
        System.out.println("Hello World");


        // CustomArrayList<Integer> arrayList = new CustomArrayList<>(2);

        // arrayList.add(1);
        // arrayList.add(2);

        // for(int i =0 ;i<100;i++){
        //     arrayList.add(i);
        // }
        // System.out.println(arrayList);
        // TestClass testClass = new TestClass();

        // DeepCloneWithSerialization cloneWithSerialization = new DeepCloneWithSerialization(testClass);
        // DeepCloneWithSerialization newObj2 = cloneWithSerialization;
        // System.out.println(cloneWithSerialization.testClass);
        // System.out.println(newObj2.testClass);



        // DeepCloneWithSerialization newObj = cloneWithSerialization.deepCopy();
        // System.out.println(newObj.testClass);
        // System.out.println(cloneWithSerialization.testClass);

        // testClass.testCustomException();

        // ThreadSafeCounter obj = new ThreadSafeCounter();
        // Thread t1 = new Thread(() -> obj.increment());
        // Thread t2 = new Thread(() -> obj.decrement());
        // Thread t3 = new Thread(() -> obj.increment());

        // t1.start();
        // t3.start();
        // t2.start();


        // CsvParser csvParser = new CsvParser();
        // csvParser.parseCsv("test.csv");



     // Double checked locking
        // ThreadSafeSingleton obj = ThreadSafeSingleton.getInstance();

        // for(int i =0;i<10;i++){
        //     Thread thread = new Thread(() -> {
        //         ThreadSafeSingleton obj = ThreadSafeSingleton.getInstance((int)(Math.random() * 100));
        //         System.out.println(Thread.currentThread().getName() + 
        //             " got instance: " + obj.data);
        //     });
        //     thread.setName("Thread_"+i);
        //     thread.start();
        // }


   
        // Comparable
        // List<ComparableExample> list = new ArrayList<>();

        // for(int i =0 ;i<100;i++){
        //     list.add(new ComparableExample(i * (int)(Math.random() * 100)));
        // }
        // // Collections.sort(list);
        
        // Comparator<ComparableExample> cmp = (o1,o2) -> {
        //     return o1.value - o2.value;
        // };
        
        // Collections.sort(list,cmp);
        // System.out.println(list);


        // HashMapExample<String,String> hashMapExample = new HashMapExample<>();
        // hashMapExample.put("A", "B");
        // hashMapExample.put("A", "B");

        // hashMapExample.put("K", "B");

        // hashMapExample.put("J", "X");

        // System.out.println(hashMapExample.get("A"));
        // System.out.println(hashMapExample.get("J"));
        // System.out.println(hashMapExample.get("K"));
        // System.out.println(hashMapExample.get("Y"));


        // System.out.println("=== BASIC CONCURRENT HASHMAP TESTING ===");
        
        // ConcurrentHashMapExample<String, String> map = new ConcurrentHashMapExample<>();
        
        // // Test 1: Basic single-threaded operations
        // System.out.println("\n--- Single Thread Test ---");
        // map.put("apple", "fruit");
        // map.put("car", "vehicle");
        // System.out.println("apple: " + map.get("apple"));
        // System.out.println("car: " + map.get("car"));
        
        // // Test 2: Simple multi-threaded test
        // System.out.println("\n--- Multi Thread Test ---");
        
        // Thread writer = new Thread(() -> {
        //     for (int i = 0; i < 3; i++) {
        //         map.put("key" + i, "value" + i);
        //         System.out.println("Writer put: key" + i);
        //         try { Thread.sleep(3000); } catch (InterruptedException e) {}
        //     }
        // }, "Writer-Thread");
        
        // Thread reader = new Thread(() -> {
        //     try { Thread.sleep(50); } catch (InterruptedException e) {}
        //     for (int i = 0; i < 3; i++) {
        //         String value = map.get("key" + i);
        //         System.out.println("Reader got key" + i + ": " + value);
        //         try { Thread.sleep(3000); } catch (InterruptedException e) {}
        //     }
        // }, "Reader-Thread");
        
        // writer.start();
        // reader.start();
        
        // try {
        //     writer.join();
        //     reader.join();
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
        
        // System.out.println("\n--- Test Completed ---");

        // TestClass testClass = new TestClass();
        // testClass.lengthTest = "ARYAN";
       
        // List<String> errors = AnnotationProcessor.processStringLengthAnnotation(testClass);
        // System.out.println("List of errors " + errors.toString());

    //     List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    //     Predicate<Integer> p = n -> n % 2 == 0;
    //    List<Integer> res =   StreamFilterExample.filterList(numbers, p);
    //     System.out.println(res);

    // Timer timer = new Timer();
    //     TimerTask task = new TimerTask() {
    //         @Override
    //         public void run() {
    //             // TODO Auto-generated method stub
    //             System.out.println("Running this task ");
                
    //         }
    //     };
    //     timer.schedule(task, 0, 2000);

    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
    executorService.scheduleAtFixedRate(() -> {
        try{

            Thread.sleep(2000);
            System.out.println("HELLO " + Thread.currentThread().getName());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }, 1, 2, TimeUnit.SECONDS); // initial delay: 1s, period: 2s


    }
}
