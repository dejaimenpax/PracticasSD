package es.Group3.BiciURJC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/errorFormulario")
    public String errorWeb(){
        return "paginaError";
    }
}
