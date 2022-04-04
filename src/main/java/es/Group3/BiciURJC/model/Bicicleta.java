package es.Group3.BiciURJC.model;

import es.Group3.BiciURJC.exceptions.IllegalStationChange;

import javax.lang.model.element.UnknownElementException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bicicleta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String num_serie;

    private String modelo;
    private String fecha;
    private EstadoBicicleta estado;

    protected Bicicleta() {}

    public Bicicleta(String modelo, String fecha) {
        super();
        this.modelo = modelo;
        this.fecha = fecha;
    }

    public String getNum_serie() {
        return num_serie;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public EstadoBicicleta getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "CicloVidaBicicletas{" +
                "num_serie='" + num_serie + '\'' +
                ", modelo='" + modelo + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }

    private void asignarBase(EstadoBicicleta es) throws IllegalStationChange {
        if (es==EstadoBicicleta.BAJA){
            this.estado = EstadoBicicleta.BAJA;
        }
        else if (es==EstadoBicicleta.EN_BASE){
            this.estado = EstadoBicicleta.EN_BASE;
            //asociar la bicicleta a la estacion
        }
        else{
            throw new IllegalStationChange("No se puede pasar de " + this.estado.toString() + " a " + es.toString());
        }
    }

    public void cambiarEstado(EstadoBicicleta es) throws IllegalStationChange {
        switch (this.estado){
            case BAJA:
                throw new IllegalStationChange("No se puede pasar de " + this.estado.toString() + " a " + es.toString());
            case SIN_BASE:
            case RESERVADA:
                asignarBase(es);
                break;
            case EN_BASE:
                if (es==EstadoBicicleta.BAJA){
                    this.estado = EstadoBicicleta.BAJA;
                }
                else if (es==EstadoBicicleta.RESERVADA){
                    this.estado = EstadoBicicleta.RESERVADA;
                    //quitar la bicicleta de la estacion
                }
                break;
            default:
                throw new RuntimeException("Error inesperado en el cambio de estado");
        }
    }
}
