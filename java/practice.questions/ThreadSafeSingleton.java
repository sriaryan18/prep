public class ThreadSafeSingleton {
    private ThreadSafeSingleton(){}

    private static volatile ThreadSafeSingleton instance;
    int data = 10;

    public static ThreadSafeSingleton getInstance(int newData){

        if(instance == null){
            synchronized(ThreadSafeSingleton.class){
                if(instance == null){
                    instance = new ThreadSafeSingleton();
                    instance.data = newData;
                }
            }
        }
        return instance;

    }
}
    