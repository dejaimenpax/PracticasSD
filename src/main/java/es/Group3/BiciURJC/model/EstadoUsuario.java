package es.Group3.BiciURJC.model;

public enum EstadoUsuario {
    ACTIVO, INACTIVO;

    @Override
    public String toString(){
        //devuelve "activo" o "inactivo"
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }
}