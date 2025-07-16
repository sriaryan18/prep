import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AnnotationProcessor {

    private static boolean validateMinMax(int min, int max, int val){
        System.out.println("Validating value " + val + " " + (val > min && val < max));
        return val > min && val < max;
    }

    public static List<String> processStringLengthAnnotation(Object obj) {
        System.out.println("Processing annotation of String length");
        Class<?> clazz = obj.getClass();
        List<String> errors = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {

                if (field.isAnnotationPresent(StringLengthAnnotation.class)) {
                    StringLengthAnnotation annotation =
                     field.getAnnotation(StringLengthAnnotation.class);
                    int stringLength = field.get(obj).toString().length();
                   boolean res =  validateMinMax(annotation.min(), annotation.max(), stringLength);
                   if(!res){
                    String error = "Field '" + field.getName() + "': " + annotation.message() 
                    + " (Expected: " + annotation.min() + "-" + annotation.max() 
                    + ", Actual: " + stringLength + ")";
                    errors.add(error);
                   }
                }
              
                
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
            

        }
        return errors;
    }
}
