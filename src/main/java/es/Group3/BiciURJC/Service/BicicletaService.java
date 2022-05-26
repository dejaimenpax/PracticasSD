package es.Group3.BiciURJC.Service;

import es.Group3.BiciURJC.Repository.BicicletasRepository;
import es.Group3.BiciURJC.model.Bicicleta;
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


    public List<Bicicleta> findAll() {
        return bicicletas.findAll();
    }

    public Bicicleta save (Bicicleta bici) {
        return bicicletas.save(bici);
    }

}
