package es.Group3.BiciURJC.Service;

import es.Group3.BiciURJC.Repository.UsuariosRepository;
import es.Group3.BiciURJC.model.EstadoUsuario;
import es.Group3.BiciURJC.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UsuariosRepository users;

    public UserService(UsuariosRepository users) {
        this.users = users;
    }

    public Usuario save(Usuario user) {
        return users.save(user);
    }

    public void delete(long id) {//se supone que al borrar un usuario se deja inactivo, pero no dice nada de sacarlo de la base de datos
        users.getById(id).setEstado(EstadoUsuario.INACTIVO);
    }

    public Optional<Usuario> findById(long id){
        return users.findById(id);
    }

    public Optional<Usuario> findByFullName(String fullName){
        return users.findByFullName(fullName);
    }

    public List<Usuario> findAll(){
        return users.findAll();
    }

    public Optional<Usuario> findByLogin(String login){
        return users.findByLogin(login);
    }
}
