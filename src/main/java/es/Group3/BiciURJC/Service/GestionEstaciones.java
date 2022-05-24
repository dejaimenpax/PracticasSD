package es.Group3.BiciURJC.Service;

import es.Group3.BiciURJC.Repository.EstacionRepository;
import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoBicicleta;

import es.Group3.BiciURJC.controller.exceptions.IllegalStateChange;
import es.Group3.BiciURJC.controller.exceptions.IllegalStationAssociation;
import es.Group3.BiciURJC.controller.exceptions.IncorrectStationCapacity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionEstaciones {
	@Autowired 
	private EstacionRepository repositorioEstacion;
	
    public void addBike(Bicicleta bk, Estacion state) throws IllegalStationAssociation, IncorrectStationCapacity {
        CicloVidaBicicletas gestor = new CicloVidaBicicletas();
        gestor.asignarBase(bk, state);
    }

    public void removeBike(Bicicleta bk, Estacion estacion) throws IllegalStateChange, IncorrectStationCapacity {
        CicloVidaBicicletas gestor = new CicloVidaBicicletas();
        gestor.cambiarEstado(bk, EstadoBicicleta.SIN_BASE, estacion);
    }
}
