package es.Group3.BiciURJC.Service;

import es.Group3.BiciURJC.model.EstadoUsuario;
import es.Group3.BiciURJC.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
@Service
public class GestionUsuarios {

    public static Hashtable<Long, Usuario> users = new Hashtable<>();

    public static void removeUser(Usuario us){
        us.setState(EstadoUsuario.INACTIVO);
    }

    public static void editUser(Usuario us, String password, String fullName, EstadoUsuario state){
        Usuario myUser = users.get(us.getId());
        myUser.setPassword(password);
        myUser.setFullName(fullName);
        myUser.setState(state);
        users.put(myUser.getId(), myUser);
    }

    public static void addUser(Usuario us){
        users.put(us.getId(), us);
    }

}
