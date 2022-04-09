package es.Group3.BiciURJC.Service;

import es.Group3.BiciURJC.exceptions.IllegalStateChange;
import es.Group3.BiciURJC.exceptions.IllegalStationAssociation;
import es.Group3.BiciURJC.exceptions.IncorrectStationCapacity;
import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoBicicleta;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Service
public class CicloVidaBicicletas {

    public static void asignarBase(Bicicleta bici, Estacion st) throws IllegalStationAssociation, IncorrectStationCapacity {
        if (bici.getEstado() == EstadoBicicleta.BAJA){
            throw new IllegalStationAssociation("No se puede asignar una base en este estado " + bici.getEstado().toString());
        }
        else if ((bici.getEstado()==EstadoBicicleta.EN_BASE)||(bici.getEstado()==EstadoBicicleta.RESERVADA)){
            throw new IllegalStationAssociation("Esta bici ya tiene una estacion, asociada anteriormente");
        }
        else{//SIN_BASE
            if(st.getListaBicis().size()<st.getSize()){
                st.getListaBicis().put(bici.getNum_serie(), bici);
                bici.setEstado(EstadoBicicleta.EN_BASE);
            }
            else{
                throw new IncorrectStationCapacity("Capacidad de estación al limite");
            }
        }
    }

    public static void cambiarEstado(Bicicleta bici, EstadoBicicleta state, Estacion st) throws IllegalStateChange, IncorrectStationCapacity {
        switch (bici.getEstado()) {
            case BAJA:
                throw new IllegalStateChange("No se puede pasar de " + bici.getEstado().toString() + " a " + state.toString());
            case SIN_BASE:
                if (state == EstadoBicicleta.BAJA) {
                    bici.setEstado(EstadoBicicleta.BAJA);
                } else if (state == EstadoBicicleta.EN_BASE) {
                    GestionEstaciones.addBike(bici, st);
                    bici.setEstado(EstadoBicicleta.EN_BASE);
                } else {//RESERVADA O SIN BASE
                    throw new IllegalStateChange("No se puede pasar de " + bici.getEstado().toString() + " a " + state.toString());
                }
            case RESERVADA://como no se va a usar en esta practica, que tire una excepcion y ya
                throw new IllegalStateChange("No se puede pasar de " + bici.getEstado().toString() + " a " + state.toString());
            case EN_BASE:
                if (state == EstadoBicicleta.BAJA) {
                    if (st.getListaBicis().remove(bici.getNum_serie()) != null) {
                        bici.setEstado(EstadoBicicleta.BAJA);
                    } else {
                        throw new IllegalStationAssociation("Esta bici " + bici.toString() + " no pertenece a esta estacion " + st.toString());
                    }
                } else if (state == EstadoBicicleta.RESERVADA) {
                    bici.setEstado(EstadoBicicleta.RESERVADA);
                } else if (state == EstadoBicicleta.SIN_BASE) {
                    if (st.getListaBicis().remove(bici.getNum_serie()) != null) {
                        bici.setEstado(EstadoBicicleta.SIN_BASE);
                    } else {
                        throw new IllegalStationAssociation("Esta bici " + bici.toString() + " no pertenece a esta estacion " + st.toString());
                    }
                } else {//RESERVADA
                    throw new IllegalStateChange("No se puede pasar de " + bici.getEstado().toString() + " a " + state.toString());
                }
            default:
                throw new RuntimeException("Error inesperado en el cambio de estado");
        }
    }

}
