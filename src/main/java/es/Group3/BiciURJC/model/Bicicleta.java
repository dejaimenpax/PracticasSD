package es.Group3.BiciURJC.model;

import es.Group3.BiciURJC.exceptions.IllegalStateChange;
import es.Group3.BiciURJC.exceptions.IllegalStationAssociation;
import es.Group3.BiciURJC.exceptions.IncorrectStationCapacity;

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

    public void asignarBase(Estacion st) throws IllegalStationAssociation, IncorrectStationCapacity {
        if (this.estado==EstadoBicicleta.BAJA){
            throw new IllegalStationAssociation("No se puede asignar una base en este estado " + this.estado.toString());
        }
        else if ((this.estado==EstadoBicicleta.EN_BASE)||(this.estado==EstadoBicicleta.RESERVADA)){
            throw new IllegalStationAssociation("Esta bici ya tiene una estacion, asociada anteriormente");
        }
        else{//SIN_BASE
            if(st.getListaBicis().size()<st.getSize()){
                st.getListaBicis().put(this.getNum_serie(), this);
                this.estado = EstadoBicicleta.EN_BASE;
            }
            else{
                throw new IncorrectStationCapacity("Capacidad de estación al limite");
            }
        }
    }

    public void cambiarEstado(EstadoBicicleta state, Estacion st) throws IllegalStateChange, IncorrectStationCapacity {
        switch (this.estado){
            case BAJA:
                throw new IllegalStateChange("No se puede pasar de " + this.estado.toString() + " a " + state.toString());
            case SIN_BASE:
                if (state==EstadoBicicleta.BAJA){
                    this.estado = EstadoBicicleta.BAJA;
                }
                else if(state==EstadoBicicleta.EN_BASE){
                    st.addBike(this);
                    this.estado = EstadoBicicleta.EN_BASE;
                }
                else{//RESERVADA O SIN BASE
                    throw new IllegalStateChange("No se puede pasar de " + this.estado.toString() + " a " + state.toString());
                }
            case RESERVADA://como no se va a usar en esta practica, que tire una excepcion y ya
                throw new IllegalStateChange("No se puede pasar de " + this.estado.toString() + " a " + state.toString());
            case EN_BASE:
                if (state==EstadoBicicleta.BAJA){
                    if(st.getListaBicis().remove(this.getNum_serie())!=null){
                        this.estado = EstadoBicicleta.BAJA;
                    }else{
                        throw new IllegalStationAssociation("Esta bici " + this.toString() +" no pertenece a esta estacion " + st.toString());
                    }
                }
                else if (state==EstadoBicicleta.RESERVADA){
                    this.estado = EstadoBicicleta.RESERVADA;
                }
                else if (state==EstadoBicicleta.SIN_BASE){
                    if(st.getListaBicis().remove(this.getNum_serie())!=null){
                        this.estado = EstadoBicicleta.SIN_BASE ;
                    }else{
                        throw new IllegalStationAssociation("Esta bici " + this.toString() + " no pertenece a esta estacion " + st.toString());
                    }
                }
                else{//RESERVADA
                    throw new IllegalStateChange("No se puede pasar de " + this.estado.toString() + " a " + state.toString());
                }
            default:
                throw new RuntimeException("Error inesperado en el cambio de estado");
        }
    }
}
