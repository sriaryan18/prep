public class MultiThreadingClass implements Runnable {

    int a = 0;
    @Override
    public  void run() {

        try{
            a++;
            System.out.println(Thread.currentThread().getName() + " is running");
            Thread.sleep(5000);
            System.out.println("EXITED BY THREAD " + a);
        }catch(Exception e){
            // handle exception
        }
        }

        
} 