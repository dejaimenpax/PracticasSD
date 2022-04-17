package es.Group3.BiciURJC.controller;

import es.Group3.BiciURJC.Repository.BicicletasRepository;
import es.Group3.BiciURJC.Repository.UsuariosRepository;
import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoBicicleta;
import es.Group3.BiciURJC.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BicicletasController {
    @Autowired
    private BicicletasRepository bicicletas;

    private Logger log = LoggerFactory.getLogger(BicicletasController.class);

    @GetMapping("/bicicletas")
    public String listbycicle(Model model) {
        List<Bicicleta> bicicletasList = bicicletas.findAll();
        model.addAttribute("bicicletasList", bicicletasList);
        return "bicicletasList";
    }


    @GetMapping("/addbicicleta")
    public String addBicicleta(Model model, @RequestParam String num_serie, @RequestParam String modelo, @RequestParam String estado) {

        Bicicleta bicicleta = new Bicicleta(num_serie,modelo, EstadoBicicleta.valueOf(estado));
        log.trace("Bicicleta identifier " + bicicleta.getId());
        bicicletas.save(bicicleta);
        log.trace("New post identifier " + bicicleta.getId());
        return "redirect:/bicicletas";//para que se añada a la lista, llamar al primer metodo de la clase controller
    }

    @GetMapping("/removebicicleta")
    public String removeBicicleta(Model model, @RequestParam long id) {
        log.trace("Bicicleta identifier " + id);
        bicicletas.deleteById(id);
        return "redirect:/bicicletas";//para que se añada a la lista, llamar al primer metodo de la clase controller
    }

}
