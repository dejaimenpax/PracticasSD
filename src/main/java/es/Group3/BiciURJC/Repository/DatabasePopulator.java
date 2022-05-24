package es.Group3.BiciURJC.Repository;

import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Coords;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoEstacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;


import static es.Group3.BiciURJC.model.EstadoBicicleta.EN_BASE;
import static es.Group3.BiciURJC.model.EstadoBicicleta.SIN_BASE;

@Component
@Profile("local")
public class DatabasePopulator {
    @Autowired
    private BicicletasRepository blogbicicletasrepository;

    @Autowired
    private EstacionRepository blogestacionrepository;

    @PostConstruct
    public void populateDB(){

        //Introducir los datos de precarga
        Estacion estacion1;
        Estacion estacion2;
        blogestacionrepository.saveAll(Arrays.asList(
                estacion1 = new Estacion("Estacion1",5, new Coords(21,32,43), new Coords(78,23,98), EstadoEstacion.ACTIVA),
                estacion2 = new Estacion("Estacion2",10, new Coords(21,32,43), new Coords(78,23,98), EstadoEstacion.ACTIVA)
        ));

        blogbicicletasrepository.saveAll(Arrays.asList(
                new Bicicleta("1A","BMX", EN_BASE, estacion1),
                new Bicicleta("1B","DH", EN_BASE, estacion1),
                new Bicicleta("1C","BMX", EN_BASE, estacion1),
                new Bicicleta("2","Street", EN_BASE, estacion1),
                new Bicicleta("3","Downhill", EN_BASE, estacion2),
                new Bicicleta("4","Paseo", EN_BASE, estacion2),
                new Bicicleta("4A","Paseo", EN_BASE, estacion2),
                new Bicicleta("5B","Montaña", SIN_BASE),
                new Bicicleta("5C","Montaña", SIN_BASE)
        ));

    }

}
