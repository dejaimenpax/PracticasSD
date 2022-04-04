package es.Group3.BiciURJC.model;

import es.Group3.BiciURJC.exceptions.IllegalStateChange;
import es.Group3.BiciURJC.exceptions.IllegalStationAssociation;

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

    private void asignarBase(EstadoBicicleta state, model.Estacion estacion) throws IllegalStateChange, IllegalStationAssociation {
        if (state==EstadoBicicleta.BAJA){
            this.estado = EstadoBicicleta.BAJA;
        }
        else if (state==EstadoBicicleta.EN_BASE){
            this.estado = EstadoBicicleta.EN_BASE;
            //estacion.addBicicleta(this) la cual lanza un IllegalStationAssociation si la estacion esta llena
        }
        else{
            throw new IllegalStateChange("No se puede pasar de " + this.estado.toString() + " a " + state.toString());
        }
    }

    public void cambiarEstado(EstadoBicicleta state, model.Estacion estacion) throws IllegalStateChange {
        switch (this.estado){
            case BAJA:
                throw new IllegalStateChange("No se puede pasar de " + this.estado.toString() + " a " + state.toString());
            case SIN_BASE:
            case RESERVADA:
                asignarBase(state, estacion);
                break;
            case EN_BASE:
                if (state==EstadoBicicleta.BAJA){
                    this.estado = EstadoBicicleta.BAJA;
                }
                else if (state==EstadoBicicleta.RESERVADA){
                    this.estado = EstadoBicicleta.RESERVADA;
                    //estacion.quitarBici(this)
                }
                break;
            default:
                throw new RuntimeException("Error inesperado en el cambio de estado");
        }
    }
}
