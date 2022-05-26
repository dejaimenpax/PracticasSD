package es.Group3.BiciURJC.Service;

import es.Group3.BiciURJC.Repository.EstacionRepository;
import es.Group3.BiciURJC.model.Estacion;
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

    public List<Estacion> findAll() {
        return estaciones.findAll();
    }

    public Estacion save(Estacion estacion) {
        return estaciones.save(estacion);
    }

}
