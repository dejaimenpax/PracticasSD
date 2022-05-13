package es.Group3.BiciURJC.Service;

import es.Group3.BiciURJC.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private ConcurrentMap<Long, Usuario> users = new ConcurrentHashMap<>();
    private AtomicLong nextId = new AtomicLong(1);
    public UserService() {
        save(new Usuario("Pepe", "Vendo moto", "Barata, barata"));
        save(new Usuario("Juan", "Compro coche", "Pago bien"));
    }
    public Collection<Usuario> findAll() {
        return users.values();
    }
    public Usuario findById(long id) {
        return users.get(id);
    }
    public void save(Usuario user) {
        if(user.getId() == null || user.getId() == 0) {
            long id = nextId.getAndIncrement();
            user.setId(id);
        }
        this.users.put(user.getId(), user);
    }
    public void deleteById(long id) {//se supone que al borrar un usuario se deja inactivo, pero no dice nada de sacarlo de la base de datos
        this.users.remove(id);
    }


}
