package es.Group3.BiciURJC.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("local")
public class DatabasePopulator {

    @Autowired
    private BicicletasRepository blogbicicletasrepository;

    @Autowired
    private UsuarioRepository blogusuariosrepository;

    @Autowired
    private EstacionRepository blogestacionrepository;

    @PostConstruct
    public void PopulateDB(){
        //Introducir los datos de precarga
    }

}
