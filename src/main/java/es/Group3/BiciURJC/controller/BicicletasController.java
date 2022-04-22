package es.Group3.BiciURJC.controller;

import es.Group3.BiciURJC.Repository.BicicletasRepository;
import es.Group3.BiciURJC.Repository.EstacionRepository;
import es.Group3.BiciURJC.Service.CicloVidaBicicletas;
import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoBicicleta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;

@Controller
public class BicicletasController {
    @Autowired
    private BicicletasRepository bicicletas;
    @Autowired
    private EstacionRepository estaciones;

    private Logger log = LoggerFactory.getLogger(BicicletasController.class);

    @GetMapping("/bicicletas")
    public String listbycicle(Model model) { //
        List<Bicicleta> bicicletasList = bicicletas.findAll();
        model.addAttribute("bicicletasList", bicicletasList);
        return "bicicletasList";
    }

    @GetMapping("/bicicletas/busqueda")
    public String view(Model model, @RequestParam String texto){
        List<Bicicleta> bicis = bicicletas.findByNum_SerieOrModelo(texto, texto);
        model.addAttribute("busqueda", bicis);
        return "busquedaBicicleta";
    }

    @GetMapping("/añadirbicicleta")
    public String addBicicleta(@RequestParam String num_serie, @RequestParam String modelo) {
        Bicicleta bicicleta = new Bicicleta(num_serie,modelo, EstadoBicicleta.SIN_BASE);
        bicicletas.save(bicicleta);
        log.trace("New post identifier " + bicicleta.getId());
        return "redirect:/bicicletas";//para que se añada a la lista, llamar al primer metodo de la clase controller
    }

    @GetMapping("/asignabasebicicleta")
    public String asignabasebicicleta(@RequestParam String numserie_bicicleta, @RequestParam String numserie_estacion) {
        Bicicleta bicicleta = bicicletas.findByNum_Serie(numserie_bicicleta);
        if (bicicleta==null){
            return "redirect:/errorFormulario";
        }
        else{
            Estacion estacion = estaciones.findByNum_Serie(numserie_estacion);
            CicloVidaBicicletas gestor = new CicloVidaBicicletas();
            gestor.asignarBase(bicicleta,estacion);
            bicicletas.save(bicicleta);
            return "redirect:/bicicletas";//para que se añada a la lista, llamar al primer metodo de la clase controller
        }
    }

    @GetMapping("/modificarbicicleta")
    public String modifiedBicicleta(@RequestParam String numserie_bicicleta, @RequestParam String state){
        Bicicleta bicicleta = bicicletas.findByNum_Serie(numserie_bicicleta);
        if (bicicleta==null){
            return "redirect:/errorFormulario";
        }
        else{
            Estacion estacion = bicicleta.getEstacion();
            EstadoBicicleta estado = EstadoBicicleta.valueOf(state);
            CicloVidaBicicletas gestor = new CicloVidaBicicletas();
            gestor.cambiarEstado(bicicleta,estado,estacion);
            bicicletas.save(bicicleta);
            return "redirect:/bicicletas";//para que se añada a la lista, llamar al primer metodo de la clase controller
        }
    }

    @GetMapping("/detallesBicicleta/{num_serie}")
    public String detallesBicicleta (Model model, @PathVariable String num_serie){
        Bicicleta bicicleta = bicicletas.findByNum_Serie(num_serie);
        model.addAttribute("detalles", bicicleta);
        return "detallesBicicleta";
    }

}
