package es.Group3.BiciURJC.Service;

import es.Group3.BiciURJC.model.Usuario;
import org.springframework.stereotype.Service;

@Service
public class GestionUsuarios {

    public static void editUser(Usuario us, String password, String fullName){
        us.setPassword(password);
        us.setFullName(fullName);
    }

}
