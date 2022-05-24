package es.Group3.BiciURJC.model;

public enum EstadoEstacion {
    ACTIVA, INACTIVA;

    @Override
    public String toString(){
        //devuelve "activa" o "inactiva"
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}