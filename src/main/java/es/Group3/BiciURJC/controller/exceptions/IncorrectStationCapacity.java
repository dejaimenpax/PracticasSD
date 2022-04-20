package es.Group3.BiciURJC.controller.exceptions;

public class IncorrectStationCapacity extends RuntimeException{
    public IncorrectStationCapacity(String errorMessage) {
        super(errorMessage);
    }
}
