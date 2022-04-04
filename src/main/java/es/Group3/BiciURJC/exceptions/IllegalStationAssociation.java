package es.Group3.BiciURJC.exceptions;

public class IllegalStationAssociation extends RuntimeException{
    public IllegalStationAssociation(String errorMessage) {
        super(errorMessage);
    }
}
