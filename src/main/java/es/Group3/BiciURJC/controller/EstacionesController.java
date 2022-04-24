package es.Group3.BiciURJC.controller;

import es.Group3.BiciURJC.Repository.EstacionRepository;
import es.Group3.BiciURJC.Service.GestionEstaciones;
import es.Group3.BiciURJC.model.*;
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
    private EstacionRepository estaciones;
    @Autowired
    private BicicletasRepository bicicletas;

    private Logger log = LoggerFactory.getLogger(EstacionesController.class);

    @GetMapping("/estaciones")
    public String liststation(Model model) {
        List<Estacion> estacionesList = estaciones.findAll();
        model.addAttribute("estacionesList", estacionesList);
        return "estacionesList";
    }

    @GetMapping("/estaciones/busqueda")
    public String view(Model model, @RequestParam String num_serie){
        List<Estacion> sts = estaciones.findByNum_SerieList(num_serie);
        model.addAttribute("busqueda", sts);
        return "busquedaEstacion";
    }

    @GetMapping("/añadirEstacion")
    public String addStation(Model model, @RequestParam String num_serie, @RequestParam int size, @RequestParam int glon, @RequestParam int mlon, @RequestParam int slon,
                             @RequestParam int glat, @RequestParam int mlat, @RequestParam int slat, @RequestParam EstadoEstacion estado) {
        Coords lon = new Coords(glon, mlon, slon);
        Coords lat = new Coords(glat, mlat, slat);
        Estacion estacion = new Estacion(num_serie, size, lon, lat, estado);
        estaciones.save(estacion);
        return "redirect:/estaciones";//para que se añada a la lista, llamar al primer metodo de la clase controller
    }
    @GetMapping("/eliminarEstacion")
    public String removeStation(Model model, @RequestParam String num_serie) {
        Estacion estacion = estaciones.findByNum_Serie(num_serie);
        if (estacion==null){
            return "redirect:/errorFormulario";
        }
        else{
            List<Bicicleta> bicis = estacion.getListaBicis();
            GestionEstaciones gestor = new GestionEstaciones();
            for(Bicicleta bk : bicis){
                gestor.removeBike(bk, estacion);
                bicicletas.save(bk);
            }
            estacion.setEstado(EstadoEstacion.INACTIVA);
            estaciones.save(estacion);
            return "redirect:/estaciones";
        }
    }
    @GetMapping("/detallesEstacion/{num_serie}")
    public String detallesEstacion (Model model, @PathVariable String num_serie){
        Estacion estacion = estaciones.findByNum_Serie(num_serie);
        model.addAttribute("detalles", estacion);
        List<Bicicleta> bicis = estacion.getListaBicis();
        model.addAttribute("detallesBicis", bicis);
        return "detallesEstacion";
    }
    @GetMapping("/modCoords")
    public String modificarCoords(Model model, @RequestParam String num_serie, @RequestParam int glon, @RequestParam int mlon, @RequestParam int slon, @RequestParam int glat, @RequestParam int mlat, @RequestParam int slat) {
        Estacion estacion = estaciones.findByNum_Serie(num_serie);
        if (estacion==null){
            return "redirect:/errorFormulario";
        }
        else {
            Coords lon = new Coords(glon, mlon, slon);
            Coords lat = new Coords(glat, mlat, slat);
            estacion.setLat(lat);
            estacion.setLon(lon);
            estaciones.save(estacion);
        }
        return "redirect:/estaciones";
    }
}
