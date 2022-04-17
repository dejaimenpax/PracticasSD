package es.Group3.BiciURJC.model;

public enum EstadoBicicleta {
    SIN_BASE, EN_BASE, RESERVADA, BAJA;


    @Override
    public String toString(){
        //devuelve "sin base", "en base", "reservada" o "baja"
        return this.name().charAt(0) + this.name().substring(1).replaceAll("_", " ").toLowerCase();
    }
}