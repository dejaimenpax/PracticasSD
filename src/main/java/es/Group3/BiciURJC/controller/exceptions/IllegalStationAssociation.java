package es.Group3.BiciURJC.controller.exceptions;

public class IllegalStationAssociation extends RuntimeException{
    public IllegalStationAssociation(String errorMessage) {
        super(errorMessage);
    }
}
