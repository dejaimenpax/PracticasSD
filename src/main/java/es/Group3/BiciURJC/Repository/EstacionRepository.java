package es.Group3.BiciURJC.Repository;

import es.Group3.BiciURJC.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstacionRepository extends JpaRepository<Estacion,Long> {
    @Query(
            value = "SELECT * FROM ESTACION WHERE NUM_SERIE = ?1",
            nativeQuery = true)
    Estacion findByNum_Serie(String num_serie);

    @Query(
            value = "SELECT * FROM ESTACION WHERE NUM_SERIE = ?1",
            nativeQuery = true)
    List<Estacion> findByNum_SerieList(String num_serie);
}
