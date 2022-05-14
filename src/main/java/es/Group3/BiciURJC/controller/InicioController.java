package es.Group3.BiciURJC.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InicioController {
    @GetMapping("/")
    public String Inicio(){
        return "Inicio";
    }
}
