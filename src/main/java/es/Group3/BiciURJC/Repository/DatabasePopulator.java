package es.Group3.BiciURJC.Repository;

import es.Group3.BiciURJC.model.Bicicleta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

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
    public void populateDB(){
        //Introducir los datos de precarga
        blogbicicletasrepository.saveAll(Arrays.asList(
                new Bicicleta("1234","BMX", "01/02/1903"),
                new Bicicleta("5678","DH", "03/05/2010")
        ));
    }

}
