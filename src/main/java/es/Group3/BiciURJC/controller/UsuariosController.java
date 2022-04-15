package es.Group3.BiciURJC.controller;

import es.Group3.BiciURJC.Repository.UsuariosRepository;
import es.Group3.BiciURJC.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UsuariosController {
    @Autowired
    private UsuariosRepository user;
    @GetMapping("/usuarios")
    public String lists(Model model) {
        List<Usuario> usuariosList = user.findAll();
        model.addAttribute("usuariosList", usuariosList);
        return "usuariosList";
    }

}
