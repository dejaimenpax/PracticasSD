package es.Group3.BiciURJC.controller;

import es.Group3.BiciURJC.Repository.EstacionRepository;
import es.Group3.BiciURJC.model.Estacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class EstacionesController {
    @Autowired
    private EstacionRepository estacion;
    @GetMapping("/estaciones")
    public String liststation(Model model) {
        List<Estacion> estacionesList = estacion.findAll();
        model.addAttribute("estacionesList", estacionesList);
        return "estacionesList";
    }
    private Logger log = LoggerFactory.getLogger(EstacionesController.class);
    @GetMapping("/addEstacion")
    public String addStation(Model model, @RequestParam Estacion st) {
        log.trace("Estacion identifier " + st.getId());
        estacion.save(st);
        log.trace("New post identifier " + st.getId());
        return "redirect:/estaciones";//para que se añada a la lista, llamar al primer metodo de la clase controller
    }
    /*@GetMapping("/removeEstacion")
    public String removeStation(Model model, @RequestParam long id) {
        log.trace("Estacion identifier " + id);
        estacion.deleteById(id);
        return "redirect:/estaciones";
    }*/
    @GetMapping("/detallesEstacion")//no funciona,
    public String detallesEstacion (Model model, long id){
        Estacion st = estacion.findById(id).get();
        estacion.delete(st);
        return "redirect:/estaciones";
    }

}
