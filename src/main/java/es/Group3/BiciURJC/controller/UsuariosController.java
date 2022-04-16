package es.Group3.BiciURJC.controller;

import es.Group3.BiciURJC.Repository.BicicletasRepository;
import es.Group3.BiciURJC.Repository.UsuariosRepository;
import es.Group3.BiciURJC.model.Bicicleta;
import es.Group3.BiciURJC.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsuariosController {
    @Autowired
    private UsuariosRepository usuarios;
    @GetMapping("/usuarios")
    public String listuser(Model model) {
        List<Usuario> usuariosList = usuarios.findAll();
        model.addAttribute("usuariosList", usuariosList);
        return "usuariosList";
    }

}
