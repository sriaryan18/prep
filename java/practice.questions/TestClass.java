import java.io.Serializable;

public class TestClass implements Serializable {
    int testValue = 0;

    public void testCustomException(){
        try{
            throw new CustomException("Custom exception occured");
        }catch(CustomException ex){
            ex.printStackTrace();
        }
    }
}
