package es.Group3.BiciURJC.Service;

import es.Group3.BiciURJC.controller.exceptions.*;
import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoBicicleta;
import es.Group3.BiciURJC.model.EstadoEstacion;
import org.springframework.stereotype.Service;
import java.util.Hashtable;
import java.util.Set;

@Service
public class GestionEstaciones {

    public String detailSt (Estacion st){
        return st.toString();
    }

    public void modCoord(Estacion st, long lon, long lat){
        st.setLat(lat);
        st.setLon(lon);
    }

    /*public void detailsBikes(Estacion st){
        Set<String> keysBike = st.getListaBicis().keySet();
        for(String key : keysBike){
            st.getListaBicis().get(key).toString();
        }
    }*/

    public static void addBike(Bicicleta bk, Estacion state) throws IllegalStationAssociation, IncorrectStationCapacity {
        CicloVidaBicicletas gestor = new CicloVidaBicicletas();
        gestor.asignarBase(bk, state);
    }

    public static void removeBike(Bicicleta bk, Estacion estacion) throws IllegalStateChange, IncorrectStationCapacity {
        CicloVidaBicicletas gestor = new CicloVidaBicicletas();
        gestor.cambiarEstado(bk, EstadoBicicleta.SIN_BASE, estacion);
    }
}
