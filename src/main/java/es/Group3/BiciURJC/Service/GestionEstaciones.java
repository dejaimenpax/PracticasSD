package es.Group3.BiciURJC.Service;

import es.Group3.BiciURJC.controller.exceptions.*;
import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoBicicleta;
import org.springframework.stereotype.Service;

@Service
public class GestionEstaciones {


    /*public void modCoord(Estacion st, long lon, long lat){
        st.setLat(lat);
        st.setLon(lon);
    }*/

    public void addBike(Bicicleta bk, Estacion state) throws IllegalStationAssociation, IncorrectStationCapacity {
        CicloVidaBicicletas gestor = new CicloVidaBicicletas();
        gestor.asignarBase(bk, state);
    }

    public void removeBike(Bicicleta bk, Estacion estacion) throws IllegalStateChange, IncorrectStationCapacity {
        CicloVidaBicicletas gestor = new CicloVidaBicicletas();
        gestor.cambiarEstado(bk, EstadoBicicleta.SIN_BASE, estacion);
    }
}
