package es.Group3.BiciURJC.controller;


import es.Group3.BiciURJC.Repository.UsuariosRepository;
import es.Group3.BiciURJC.Service.GestionUsuarios;
import es.Group3.BiciURJC.model.EstadoUsuario;
import es.Group3.BiciURJC.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsuariosController {
    @Autowired
    private UsuariosRepository usuarios;
    
    private Logger log = LoggerFactory.getLogger(UsuariosController.class);
    
    @GetMapping("/usuarios")
    public String listuser(Model model) {
        List<Usuario> usuariosList = usuarios.findAll();
        model.addAttribute("usuariosList", usuariosList);
        return "usuariosList";
    }


    @GetMapping("/addUsuario")
    public String addUsuario(@RequestParam String fullName, @RequestParam String password) {
        Usuario usuario = new Usuario(password, fullName);
        usuarios.save(usuario);
        log.trace("New post identifier " + usuario.getId());
        return "redirect:/usuarios";//para que se añada a la lista, llamar al primer metodo de la clase controller
    }

    @GetMapping("/modifiedusuario")
    public String modifiedUsuario(@RequestParam String fullName,@RequestParam String changename, @RequestParam String password){
        Usuario usuario = usuarios.findByFullName(fullName);
        GestionUsuarios gestor= new GestionUsuarios();
        gestor.editUser(usuario, password, changename);
        usuarios.save(usuario);
        return "redirect:/usuarios";//para que se añada a la lista, llamar al primer metodo de la clase controller
    }
    

    @GetMapping("/removeusuario")
    public String removeUsuario(@RequestParam String fullName) {
        log.trace("Usuario identificador " + fullName);
        Usuario usuario = usuarios.findByFullName(fullName);
        usuario.setState(EstadoUsuario.INACTIVO);
        usuarios.save(usuario);
        return "redirect:/usuarios";//para que se añada a la lista, llamar al primer metodo de la clase controller
    }


}
