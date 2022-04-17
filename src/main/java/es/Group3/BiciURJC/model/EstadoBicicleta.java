package es.Group3.BiciURJC.model;

public enum EstadoBicicleta {
    SIN_BASE(1), EN_BASE(2), RESERVADA(3), BAJA(4);

    private int value;
    EstadoBicicleta(int i) {
        this.value = i;
    }

    @Override
    public String toString(){
        //devuelve "sin base", "en base", "reservada" o "baja"
        return this.name().charAt(0) + this.name().substring(1).replaceAll("_", " ").toLowerCase();
    }
}