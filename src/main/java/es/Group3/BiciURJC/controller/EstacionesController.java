package es.Group3.BiciURJC.controller;

import es.Group3.BiciURJC.Repository.EstacionRepository;
import es.Group3.BiciURJC.model.Estacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EstacionesController {
    @Autowired
    private EstacionRepository estacion;
    @GetMapping("/estaciones")
    public String lists(Model model) {
        List<Estacion> estacionesList = estacion.findAll();
        model.addAttribute("estacionesList", estacionesList);
        return "estacionesList";
    }

}
