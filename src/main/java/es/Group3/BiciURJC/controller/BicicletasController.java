package es.Group3.BiciURJC.controller;

import es.Group3.BiciURJC.Repository.BicicletasRepository;
import es.Group3.BiciURJC.model.Bicicleta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BicicletasController {
    @Autowired
    private BicicletasRepository bicicletas;
    @GetMapping("/list")
    public String lists(Model model) {
        List<Bicicleta> bicicletasList = bicicletas.findAll();
        model.addAttribute("bicicletasList", bicicletasList);
        return "bicicletasList";
    }

}