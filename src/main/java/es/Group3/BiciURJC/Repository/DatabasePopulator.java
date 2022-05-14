package es.Group3.BiciURJC.Repository;

import es.Group3.BiciURJC.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
@Profile("local")
public class DatabasePopulator {

    @Autowired
    private UsuariosRepository blogusuariosrepository;

    @PostConstruct
    public void populateDB(){

        blogusuariosrepository.saveAll(Arrays.asList(
                new Usuario("123","Juan", "juanito", 250)
        ));
    }

}
