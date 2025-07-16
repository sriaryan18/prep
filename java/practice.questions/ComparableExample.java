public class ComparableExample implements Comparable<ComparableExample> {

    int value=0;

   public ComparableExample(int v){
        value = v;
    }

    @Override
    public int compareTo(ComparableExample o) {
           return this.value - o.value;
    }

    @Override
    public String toString() {
        return "ComparableExample{value=" + value + "} \n";
    }
    
    
}
