package es.Group3.BiciURJC.Repository;

import es.Group3.BiciURJC.model.Bicicleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BicicletasRepository extends JpaRepository<Bicicleta,Long> {
    @Query(
            value = "SELECT * FROM BICICLETA WHERE NUM_SERIE = ?1",
            nativeQuery = true)
    Bicicleta findByNum_Serie(String num_serie);

}
