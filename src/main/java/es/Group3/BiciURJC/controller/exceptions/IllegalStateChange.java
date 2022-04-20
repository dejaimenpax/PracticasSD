package es.Group3.BiciURJC.controller.exceptions;

public class IllegalStateChange extends RuntimeException{
    public IllegalStateChange(String errorMessage) {
        super(errorMessage);
    }
}
