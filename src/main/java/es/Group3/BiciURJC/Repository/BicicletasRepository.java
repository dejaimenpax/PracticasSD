package es.Group3.BiciURJC.Repository;

import es.Group3.BiciURJC.model.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BicicletasRepository extends JpaRepository<Bicicleta,Long> {
}
