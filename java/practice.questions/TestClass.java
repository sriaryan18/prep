import java.io.Serializable;

public class TestClass implements Serializable {
    int testValue = 0;
    
    @StringLengthAnnotation(min = 2,max=50)
    String lengthTest;

    public void testCustomException(){
        try{
            throw new CustomException("Custom exception occured");
        }catch(CustomException ex){
            ex.printStackTrace();
        }
    }
}
