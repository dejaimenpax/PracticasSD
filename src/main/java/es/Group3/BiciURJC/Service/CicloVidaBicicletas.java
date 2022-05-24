package es.Group3.BiciURJC.Service;

import es.Group3.BiciURJC.Repository.BicicletasRepository;
import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoBicicleta;
import es.Group3.BiciURJC.controller.exceptions.IllegalStateChange;
import es.Group3.BiciURJC.controller.exceptions.IllegalStationAssociation;
import es.Group3.BiciURJC.controller.exceptions.IncorrectStationCapacity;
import org.springframework.stereotype.Service;

@Service
public class CicloVidaBicicletas {

	private BicicletasRepository bicicletasRepo;
	
    public void asignarBase(Bicicleta bici, Estacion state) throws IllegalStationAssociation, IncorrectStationCapacity {
        if (bici.getEstado().getLista().element() == EstadoBicicleta.BAJA){
            throw new IllegalStationAssociation("No se puede asignar una base en este estado " + bici.getEstado().toString());
        }
        else if ((bici.getEstado().getLista().element()==EstadoBicicleta.EN_BASE)||(bici.getEstado().getLista().element()==EstadoBicicleta.RESERVADA)){
            throw new IllegalStationAssociation("Esta bici ya tiene una estacion, asociada anteriormente");
        }
        else{//SIN_BASE
            if(state.getListaBicis().size()<state.getSize()){
                state.getListaBicis().add(bici);
                bici.getEstado().getLista().addFirst(EstadoBicicleta.EN_BASE);
                bici.setEstacion(state);
            }
            else{
                throw new IncorrectStationCapacity("Capacidad de estación al limite");
            }
        }
    }

    public void cambiarEstado(Bicicleta bici, EstadoBicicleta state, Estacion estacion) throws IllegalStateChange, IncorrectStationCapacity {
        switch (bici.getEstado().getLista().element()) {
            case BAJA:
                throw new IllegalStateChange("No se puede pasar de " + bici.getEstado().toString() + " a " + state.toString());
            case SIN_BASE:
                if (state == EstadoBicicleta.BAJA) {
                    bici.getEstado().getLista().addFirst(EstadoBicicleta.BAJA);
                } else if (state == EstadoBicicleta.EN_BASE) {
                    GestionEstaciones gestor = new GestionEstaciones();
                    gestor.addBike(bici, estacion);
                    bici.getEstado().getLista().addFirst(EstadoBicicleta.EN_BASE);
                } else {//RESERVADA O SIN BASE
                    throw new IllegalStateChange("No se puede pasar de " + bici.getEstado().toString() + " a " + state.toString());
                }
                break;
            case RESERVADA://como no se va a usar en esta practica, que tire una excepcion y ya
                throw new IllegalStateChange("No se puede pasar de " + bici.getEstado().toString() + " a " + state.toString());
            case EN_BASE:
                if (state == EstadoBicicleta.BAJA) {
                    if (estacion.getListaBicis().contains(bici)) {
                        bici.getEstado().getLista().addFirst(EstadoBicicleta.BAJA);
                    } else {
                        throw new IllegalStationAssociation("Esta bici " + bici.toString() + " no pertenece a esta estacion " + estacion.toString());
                    }
                } else if (state == EstadoBicicleta.RESERVADA) {
                    bici.getEstado().getLista().addFirst(EstadoBicicleta.RESERVADA);
                } else if (state == EstadoBicicleta.SIN_BASE) {
                    if (estacion.getListaBicis().contains(bici)) {
                        bici.getEstado().getLista().addFirst(EstadoBicicleta.SIN_BASE);
                        bici.setEstacion(null);
                    } else {
                        throw new IllegalStationAssociation("Esta bici " + bici.toString() + " no pertenece a esta estacion " + estacion.toString());
                    }
                } else {//RESERVADA
                    throw new IllegalStateChange("No se puede pasar de " + bici.getEstado().toString() + " a " + state.toString());
                }
                break;
            default:
                throw new RuntimeException("Error inesperado en el cambio de estado");
        }
    }
}
