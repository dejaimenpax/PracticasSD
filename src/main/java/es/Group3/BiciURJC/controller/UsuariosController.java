package es.Group3.BiciURJC.controller;


import es.Group3.BiciURJC.Repository.UsuariosRepository;
import es.Group3.BiciURJC.Service.UserService;
import es.Group3.BiciURJC.model.EstadoUsuario;
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
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UsuariosController {
    @Autowired
    private UserService usuarios;
    
    private Logger log = LoggerFactory.getLogger(UsuariosController.class);
    
    @GetMapping("/")
    public Collection<Usuario> getUsers() {
        return usuarios.findAll();
    }

    @GetMapping("/usuarios/busqueda")
    public String view(Model model, @RequestParam String fullName){
        Optional<Usuario> usuario = usuarios.findByFullName(fullName);
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
    public ResponseEntity<Usuario> replaceUser(@PathVariable long id,
                                            @RequestBody Usuario newUser) {
        Optional<Usuario> user = usuarios.findById(id);
        if (user.isPresent()) {
            newUser.setId(id);
            usuarios.save(newUser);
            return ResponseEntity.ok(newUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUser(@PathVariable long id) {
        Optional<Usuario> user = usuarios.findById(id);
        if (user.isPresent()) {
            user.get().setEstado(EstadoUsuario.INACTIVO);
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/detallesUsuario/{fullName}")
    public String detallesUsuario (Model model, @PathVariable String fullName){
        Optional<Usuario> usuario = usuarios.findByFullName(fullName);
        model.addAttribute("detalles", usuario);
        return "detallesUsuario";
    }

    @PutMapping("/payment/{id}")
    public ResponseEntity<Usuario> payment(@PathVariable long id){
        Optional<Usuario> user = usuarios.findById(id);
        if ((user.isPresent())) {
            if(user.get().getSaldo()>=5) {//he supuesto 2,5€ alquiler y otros 2,5€ la fianza
                user.get().setSaldo(user.get().getSaldo() - 5);
                usuarios.save(user.get());
                return ResponseEntity.ok(user.get());
            }else{
                System.out.println("No dispone de suficiente saldo para realizar la operacion");//tal vez sacar esto en una excepcion?
                return ResponseEntity.unprocessableEntity().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/devolution/{id}")
    public ResponseEntity<Usuario> devolution(@PathVariable long id){
        Optional<Usuario> user = usuarios.findById(id);
        if ((user.isPresent())) {

            user.get().setSaldo(user.get().getSaldo() + 2.5);//2,5€ de la fianza
            usuarios.save(user.get());
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
