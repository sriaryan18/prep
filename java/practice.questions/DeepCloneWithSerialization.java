import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DeepCloneWithSerialization implements Serializable {


    public int test = 0;
    public TestClass testClass;

    public DeepCloneWithSerialization(TestClass testClass){
        this.testClass = testClass;
    }


    public DeepCloneWithSerialization deepCopy(){

        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
    
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            DeepCloneWithSerialization clazz = (DeepCloneWithSerialization) ois.readObject();
            return clazz;

        }catch(Exception e){
            System.out.println(e);
            return null;
        }


    }

    
}
