package es.Group3.BiciURJC.Repository;

import es.Group3.BiciURJC.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuario,Long> {
    @Query(
            value = "SELECT * FROM USUARIO WHERE FULL_NAME = ?1",
            nativeQuery = true)
    Optional<Usuario> findByFullName(String fullName);

    @Query(
            value = "SELECT * FROM USUARIO WHERE LOGIN = ?1",
            nativeQuery = true)
    Optional<Usuario> findByLogin(String Login);
}
