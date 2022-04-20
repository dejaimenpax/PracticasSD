package es.Group3.BiciURJC.Repository;

import es.Group3.BiciURJC.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static es.Group3.BiciURJC.model.Coords.coord;
import static es.Group3.BiciURJC.model.EstadoBicicleta.SIN_BASE;

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
                new Bicicleta("1234","BMX", SIN_BASE),
                new Bicicleta("5678","DH", SIN_BASE)
        ));

        blogestacionrepository.saveAll(Arrays.asList(
                new Estacion("Hola",5, coord, coord, EstadoEstacion.ACTIVA)
        ));

        blogusuariosrepository.saveAll(Arrays.asList(
                new Usuario("8","Juan"),
                new Usuario("12", "Pedro")
        ));
    }

}
