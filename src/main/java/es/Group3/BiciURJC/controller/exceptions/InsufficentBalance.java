package es.Group3.BiciURJC.controller.exceptions;

public class InsufficentBalance extends RuntimeException{
    public InsufficentBalance(String errorMessage) {
        super(errorMessage);
    }
}
