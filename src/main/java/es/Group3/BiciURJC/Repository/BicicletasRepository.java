package es.Group3.BiciURJC.Repository;

import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoBicicleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BicicletasRepository extends JpaRepository<Bicicleta,Long> {

    @Modifying
    @Query(
            value = "UPDATE bicicleta SET estado = :estado WHERE id = :id",
            nativeQuery = true)
    void updateEstadoById(@Param("id") Long id, @Param("estado") EstadoBicicleta estado);

    @Modifying
    @Query(value = "UPDATE bicicleta SET estacion = :estacion WHERE id = :id",
            nativeQuery = true)
    void updateEstacionById(@Param("id") Long id, @Param("estacion") Estacion estacion);
}
