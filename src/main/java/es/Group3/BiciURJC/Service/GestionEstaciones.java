package es.Group3.BiciURJC.Service;

import es.Group3.BiciURJC.exceptions.IllegalStateChange;
import es.Group3.BiciURJC.exceptions.IllegalStationAssociation;
import es.Group3.BiciURJC.exceptions.IncorrectStationCapacity;
import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoBicicleta;
import es.Group3.BiciURJC.model.EstadoEstacion;
import org.springframework.stereotype.Service;
import java.util.Hashtable;
import java.util.Set;

@Service
public class GestionEstaciones {

    public Hashtable<Long, Estacion> stations = new Hashtable<>();

    public void addSt(Estacion st){
        stations.put(st.getId(), st);
    }

    public String detailSt (Estacion st){
        return st.toString();
    }

    public void modCoord(Estacion st, long lon, long lat){
        Estacion mySt = stations.get(st.getId());
        mySt.setLat(lat);
        mySt.setLon(lon);
        stations.put(mySt.getId(), mySt);
    }

    /*public void deleteSt(Estacion st){
        st.setState(EstadoEstacion.INACTIVA);
        Set<String> keysBike = st.getListaBicis().keySet();
        for(String key : keysBike){
            removeBike(st.getListaBicis().get(key), st);
        }
    }*/

    /*public void detailsBikes(Estacion st){
        Set<String> keysBike = st.getListaBicis().keySet();
        for(String key : keysBike){
            st.getListaBicis().get(key).toString();
        }
    }*/

    public static void addBike(Bicicleta bk, Estacion st) throws IllegalStationAssociation, IncorrectStationCapacity {
        CicloVidaBicicletas.asignarBase(bk, st);
    }

    public Estacion getEstacion(long id){
        return stations.get(id);
    }

    public static void removeBike(Bicicleta bk, Estacion es) throws IllegalStateChange, IncorrectStationCapacity {
        CicloVidaBicicletas.cambiarEstado(bk, EstadoBicicleta.SIN_BASE, es);
    }
}
