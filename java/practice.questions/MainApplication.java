import java.security.SecureRandom;

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
        TestClass testClass = new TestClass();

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




        // ThreadSafeSingleton obj = ThreadSafeSingleton.getInstance();

        for(int i =0;i<10;i++){
            Thread thread = new Thread(() -> {
                ThreadSafeSingleton obj = ThreadSafeSingleton.getInstance((int)(Math.random() * 100));
                System.out.println(Thread.currentThread().getName() + 
                    " got instance: " + obj.data);
            });
            thread.setName("Thread_"+i);
            thread.start();
        }
        


    }
}
