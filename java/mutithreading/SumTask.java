import java.util.concurrent.*;

public class SumTask extends RecursiveTask<Integer> {

    private int start, end;
    private int[] array;

    SumTask(int start,int end,int[] array){

        this.start = start;
        this.end = end;
        this.array = array;

    }


    @Override
    protected Integer compute(){
        if(end - start <= 2){
            int sum = 0;
            for(int i=start;i<end; i++){
                sum+=array[i];
            }
            return sum;
        }

        int mid = start + (end - start) / 2; 

        SumTask left = new SumTask(start, mid, array);
        SumTask right = new SumTask(mid, end, array);

        left.fork();
        right.fork();

        int leftResult = left.join();
        int rightResult = right.join();

        return leftResult + rightResult;
    }
    
}
