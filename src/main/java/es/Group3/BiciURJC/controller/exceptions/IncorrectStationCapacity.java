package es.Group3.BiciURJC.exceptions;

public class IncorrectStationCapacity extends RuntimeException{
    public IncorrectStationCapacity(String errorMessage) {
        super(errorMessage);
    }
}
