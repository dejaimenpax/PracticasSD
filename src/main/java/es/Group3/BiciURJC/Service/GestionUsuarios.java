package es.Group3.BiciURJC.Service;

import es.Group3.BiciURJC.model.EstadoUsuario;
import es.Group3.BiciURJC.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
@Service
public class GestionUsuarios {

    public static void removeUser(Usuario us){
        us.setEstado(EstadoUsuario.INACTIVO);
    }

    public static void editUser(Usuario us, String password, String fullName){
        us.setPassword(password);
        us.setFullName(fullName);
    }

}
