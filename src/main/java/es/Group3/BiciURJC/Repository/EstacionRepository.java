package es.Group3.BiciURJC.Repository;

import es.Group3.BiciURJC.model.Coords;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoEstacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EstacionRepository extends JpaRepository<Estacion,Long> {
    @Modifying
    @Query(
            value = "UPDATE Estacion st SET st.lat = :lat WHERE st.id = :id",
            nativeQuery = true)
    void updateLatById(@Param("id") Long id, @Param("lat") Coords lat);

    @Modifying
    @Query(
            value = "UPDATE Estacion st SET st.lon = :lon WHERE st.id = :id",
            nativeQuery = true)
    void updateLonById(@Param("id") Long id, @Param("lon") Coords lon);

    @Modifying
    @Query(
            value = "UPDATE Estacion st SET st.estado = :estado WHERE st.id = :id",
            nativeQuery = true)
    void updateStationStateById(@Param("id") Long id, @Param("estado") EstadoEstacion estado);

}
