package es.Group3.BiciURJC.controller;

import es.Group3.BiciURJC.Repository.BicicletasRepository;
import es.Group3.BiciURJC.model.Bicicleta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BusquedaBicisController {

    @Autowired
    private BicicletasRepository bicicletas;

    @GetMapping("/searchbicicletas")
    public String searchBicicletas(Model model){
        List<Bicicleta> lista;
        List<Long> ids = new ArrayList<>(1);
        ids.add(1L);
        lista = bicicletas.findAllById(ids);
        model.addAttribute("searchBicicletasList", lista);
        return "view-busqueda";
    }

}
