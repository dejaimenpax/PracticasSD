package es.Group3.BiciURJC.Repository;

import es.Group3.BiciURJC.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {
}