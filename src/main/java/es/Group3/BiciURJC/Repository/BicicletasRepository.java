package es.Group3.BiciURJC.Repository;

import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BicicletasRepository extends JpaRepository<Bicicleta,Long> {

    @Query(
            value = "SELECT * FROM BICICLETA WHERE NUM_SERIE = ?1 OR MODELO = ?1",
            nativeQuery = true)
    List<Bicicleta> findByNum_SerieOrModelo(String num_serie);

    @Query(
            value = "SELECT * FROM BICICLETA WHERE NUM_SERIE = ?1",
            nativeQuery = true)
    Bicicleta findByNum_Serie(String num_serie);
}
