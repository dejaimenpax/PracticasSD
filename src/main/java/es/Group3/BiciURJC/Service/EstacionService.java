package es.Group3.BiciURJC.Service;

import es.Group3.BiciURJC.Repository.EstacionRepository;
import es.Group3.BiciURJC.model.Coords;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoEstacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstacionService {
    @Autowired
    private EstacionRepository estaciones;

    public EstacionService(EstacionRepository estaciones) {
        this.estaciones=estaciones;
    }

    public Optional<Estacion> findOne(Long id){
        return estaciones.findById(id);
    }

    public boolean exist(Long id) {
        return estaciones.existsById(id);
    }
    public Optional<Estacion> findOne(long id) {
        return estaciones.findById(id);
    }

    public List<Estacion> findAll() {
        return estaciones.findAll();
    }

    public Estacion save(Estacion estacion) {
        return estaciones.save(estacion);
    }
    public void editarCoordenadas(Long id, Coords lat, Coords lon) {
        estaciones.updateLatById(id, lat);
        estaciones.updateLonById(id, lon);
    }
    public void editarActivo(Long id, EstadoEstacion estado) {
        estaciones.updateStationStateById(id, estado);
    }
    public void delete(Long Id) {
        estaciones.deleteById(Id);
    }

}
