package es.Group3.BiciURJC.controller;

import es.Group3.BiciURJC.Repository.EstacionRepository;
import es.Group3.BiciURJC.Service.CicloVidaBicicletas;
import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Estacion;
import es.Group3.BiciURJC.model.EstadoBicicleta;
import es.Group3.BiciURJC.model.EstadoEstacion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import es.Group3.BiciURJC.Repository.BicicletasRepository;

import java.util.List;

@Controller
public class EstacionesController {
    @Autowired
    private EstacionRepository estacion;
    @Autowired
    private BicicletasRepository bicicletas;
    @GetMapping("/estaciones")
    public String liststation(Model model) {
        List<Estacion> estacionesList = estacion.findAll();
        model.addAttribute("estacionesList", estacionesList);
        return "estacionesList";
    }

    private Logger log = LoggerFactory.getLogger(EstacionesController.class);
    @GetMapping("/addEstacion")
    public String addStation(Model model, @RequestParam String num_serie, @RequestParam int size, @RequestParam int lon, @RequestParam int lat, @RequestParam EstadoEstacion state) {
        Estacion st = new Estacion(num_serie, size, lon, lat, state);
        estacion.save(st);
        return "redirect:/estaciones";//para que se añada a la lista, llamar al primer metodo de la clase controller
    }
    @GetMapping("/removeEstacion")
    public String removeStation(Model model, @RequestParam String num_serie) {
        Estacion st = estacion.findByNum_Serie(num_serie);
        List<Bicicleta> bicis = st.getListaBicis();
        CicloVidaBicicletas gestor = new CicloVidaBicicletas();
        for(Bicicleta bk : bicis){
            gestor.cambiarEstado(bk, EstadoBicicleta.SIN_BASE, st);
            bk.setEstacion(null);
            bicicletas.save(bk);
        }
        st.setState(EstadoEstacion.INACTIVA);
        estacion.save(st);
        return "redirect:/estaciones";
    }
    @GetMapping("/detallesEstacion/{num_serie}")
    public String detallesEstacion (Model model, @PathVariable String num_serie){
        Estacion st = estacion.findByNum_Serie(num_serie);
        model.addAttribute("detalles", st);
        List<Bicicleta> bicis = st.getListaBicis();
        model.addAttribute("detallesBicis", bicis);
        return "detallesEstacion";
    }

    @GetMapping("/estaciones/busqueda")
    public String view(Model model, @RequestParam String num_serie){
        Estacion st = estacion.findByNum_Serie(num_serie);
        System.out.println("He encontrado la estacion con id "+ st.getId());
        model.addAttribute("busqueda", st);
        return "busquedaEstacion";
    }

}
