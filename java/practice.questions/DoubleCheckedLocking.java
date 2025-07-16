public class DoubleCheckedLocking {
    private static DoubleCheckedLocking instance;

    private DoubleCheckedLocking(){}

    public static DoubleCheckedLocking getInstance(){
        if(DoubleCheckedLocking.instance == null){
            synchronized(DoubleCheckedLocking.class){
                if(DoubleCheckedLocking.instance == null){
                    instance = new DoubleCheckedLocking();
                }
            }
        }
        return instance;
    }


}
