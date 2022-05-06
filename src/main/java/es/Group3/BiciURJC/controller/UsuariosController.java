package es.Group3.BiciURJC.controller;


import es.Group3.BiciURJC.Service.UserService;
import es.Group3.BiciURJC.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;


@RestController
@RequestMapping("/users")
public class UsuariosController {
    @Autowired
    private UserService usuarios;
    
    private Logger log = LoggerFactory.getLogger(UsuariosController.class);
    
    @GetMapping("/")
    public Collection<Usuario> getusers() {
        return usuarios.findAll();
    }

    @GetMapping("/usuarios/busqueda")
    public String view(Model model, @RequestParam String fullName){
        Usuario usuario = usuarios.findByFullName(fullName);
        model.addAttribute("busqueda", usuario);
        return "busquedaUsuarios";
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> createUser(@RequestBody Usuario user) {
        usuarios.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> replaceUser(@PathVariable long id, @RequestBody Usuario newUser) {
        Usuario user = usuarios.findById(id);
        if (user != null) {
            newUser.setId(id);
            usuarios.save(newUser);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUser(@PathVariable long id) {
        Usuario user = usuarios.findById(id);
        if (user != null) {
            usuarios.deleteById(id);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/detallesUsuario/{fullName}")
    public String detallesUsuario (Model model, @PathVariable String fullName){
        Usuario usuario = usuarios.findByFullName(fullName);
        model.addAttribute("detalles", usuario);
        return "detallesUsuario";
    }
}
