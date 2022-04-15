package es.Group3.BiciURJC.Repository;

import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.Usuario;
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
    private UsuariosRepository blogusuariosrepository;

    @Autowired
    private EstacionRepository blogestacionrepository;

    @PostConstruct
    public void populateDB(){
        //Introducir los datos de precarga
        blogbicicletasrepository.saveAll(Arrays.asList(
                new Bicicleta("1234","BMX", "01/02/1903"),
                new Bicicleta("5678","DH", "03/05/2010")
        ));
        blogestacionrepository.saveAll(Arrays.asList(
                new Estacion("Hola",5,2,2,true)
        ));
        blogusuariosrepository.saveAll(Arrays.asList(
                new Usuario(8,"abc","Juan")
        ));
    }

}
