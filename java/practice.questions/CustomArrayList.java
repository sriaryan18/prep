
import javax.management.RuntimeErrorException;

@SuppressWarnings("unchecked")
public class CustomArrayList<T> {

    private T[] arr;
    private int occupiedSize = 0;
    private int THREASH_HOLD_SIZE_DELTA=5;

    public CustomArrayList(int size) {
        this.arr = createNewArrayWithSize(size);
    }

    private T[] createNewArrayWithSize(int size) {
        return (T[]) new Object[size];
    }

    private void copyExistingValuesInNewArray(T[] newArray) {
        if (arr.length > newArray.length) {
            throw new RuntimeException("Can not copy because of size issues " + arr.length + " " + newArray.length);
        }
        for (int i = 0; i < this.arr.length; i++) {
            newArray[i] = arr[i];
        }
        this.arr = newArray;
    }

    private void increaseSize(int size){
        T[] newArray = createNewArrayWithSize(size);
        copyExistingValuesInNewArray(newArray);
        
    }

    private boolean validateSizeDelta(){
        return (arr.length - occupiedSize) > THREASH_HOLD_SIZE_DELTA;
    }

    public void add(T el) {
        if (!validateSizeDelta()) {

            increaseSize((occupiedSize + 1) * 2);
            System.out.println("SIZE: "+ arr.length + " " + occupiedSize);
        } 
            this.arr[occupiedSize] = el;
            occupiedSize++;
        
    }

    public T get(int index) {
        if (index >= arr.length) {
            throw new IndexOutOfBoundsException();
        }
        return arr[index];
    }

    public T pop() {
        if (arr.length == 0 || occupiedSize == 0) {
            throw new RuntimeException("Array is empty");

        }
        T item = arr[occupiedSize - 1];
        arr[occupiedSize - 1] = null;
        occupiedSize--;
        return item;
    }

    public void addByIndex(int idx, T item) {
        if (idx >= arr.length || idx >= occupiedSize) {
            throw new IndexOutOfBoundsException();
        }
        if(!validateSizeDelta()){
            increaseSize(occupiedSize * 2);
        }
        for(int i = occupiedSize ; i != idx ; i--){
            arr[i+1] = arr[i];
        }
        arr[idx] = item;
        occupiedSize++;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int i = 0;i<occupiedSize;i++){
            // System.out.println();
            str.append("Element at idx: " + i + " is " + arr[i] +  "\n");
        }
        return str.toString();
    }

}
