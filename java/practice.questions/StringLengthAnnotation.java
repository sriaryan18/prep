import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface StringLengthAnnotation {

    int min() default 10;
    int max() default Integer.MAX_VALUE;
    String message() default "String length is invalid";
    
}
