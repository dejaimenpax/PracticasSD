package es.Group3.BiciURJC.Service;

import es.Group3.BiciURJC.Repository.BicicletasRepository;
import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoBicicleta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BicicletaService {

    private BicicletasRepository bicicletas;

    public BicicletaService(BicicletasRepository bicicletas){
        this.bicicletas=bicicletas;
    }

    public Optional<Bicicleta> findOne(Long id){
        return bicicletas.findById(id);
    }

    public boolean exist(Long id) {
        return bicicletas.existsById(id);
    }

    public List<Bicicleta> findAll() {
        return bicicletas.findAll();
    }

    public void editarEstado(Long id, EstadoBicicleta estado) {
        bicicletas.updateEstadoById(id, estado);
    }

    public Bicicleta save (Bicicleta bici) {
        return bicicletas.save(bici);
    }

    public void delete(Long id) {
        bicicletas.deleteById(id);
    }
    public void establecerEstacion (Long id, Estacion estacion) {
        bicicletas.updateEstacionById(id, estacion);
    }
}
