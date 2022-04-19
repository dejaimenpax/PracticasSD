package es.Group3.BiciURJC.exceptions;

public class IllegalStateChange extends RuntimeException{
    public IllegalStateChange(String errorMessage) {
        super(errorMessage);
    }
}
