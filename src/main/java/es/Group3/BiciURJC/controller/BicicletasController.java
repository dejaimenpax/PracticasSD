package es.Group3.BiciURJC.controller;

import es.Group3.BiciURJC.Repository.BicicletasRepository;
import es.Group3.BiciURJC.Repository.EstacionRepository;
import es.Group3.BiciURJC.Repository.UsuariosRepository;
import es.Group3.BiciURJC.Service.CicloVidaBicicletas;
import es.Group3.BiciURJC.Service.GestionEstaciones;
import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoBicicleta;
import es.Group3.BiciURJC.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.OneToOne;
import java.util.List;
import java.util.Optional;

@Controller
public class BicicletasController {
    @Autowired
    private BicicletasRepository bicicletas;

    @Autowired
    private EstacionRepository estaciones;

    private Logger log = LoggerFactory.getLogger(BicicletasController.class);

    @GetMapping("/bicicletas")
    public String listbycicle(Model model) {
        List<Bicicleta> bicicletasList = bicicletas.findAll();
        model.addAttribute("bicicletasList", bicicletasList);
        return "bicicletasList";
    }


    @GetMapping("/addbicicleta")
    public String addBicicleta(Model model, @RequestParam String num_serie, @RequestParam String modelo) {
        Bicicleta bicicleta = new Bicicleta(num_serie,modelo, EstadoBicicleta.SIN_BASE);
        log.trace("Bicicleta identifier " + bicicleta.getId());
        bicicletas.save(bicicleta);
        log.trace("New post identifier " + bicicleta.getId());
        return "redirect:/bicicletas";//para que se añada a la lista, llamar al primer metodo de la clase controller
    }

    @GetMapping("/modifedbicicleta")
    public String modifiedBicicleta(Model model, @RequestParam long id_bicicleta, @RequestParam String state) {
        Bicicleta bicicleta = bicicletas.findById(id_bicicleta).get();
        CicloVidaBicicletas.cambiarEstado(bicicleta, EstadoBicicleta.valueOf(state),bicicleta.getEstacion());
        //log.trace("New post identifier " + bicicleta.getId());
        return "redirect:/bicicletas";//para que se añada a la lista, llamar al primer metodo de la clase controller
    }

    @DeleteMapping("/removebicicleta")
    public String removeBicicleta(Model model, @RequestParam long id) {
        log.trace("Bicicleta identifier " + id);
        bicicletas.deleteById(id);
        return "redirect:/bicicletas";//para que se añada a la lista, llamar al primer metodo de la clase controller
    }

}
