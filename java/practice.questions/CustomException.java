public class CustomException extends RuntimeException {
    String message;
    public CustomException(String message){
        super(message);
        this.message = message;
    }
}
