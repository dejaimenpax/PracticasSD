package es.Group3.BiciURJC.controller;


import es.Group3.BiciURJC.DTO.UsuarioDto;
import es.Group3.BiciURJC.Service.UserService;
import es.Group3.BiciURJC.model.EstadoUsuario;
import es.Group3.BiciURJC.model.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletException;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UsuariosController {
    @Autowired
    private UserService usuarios;
    
    @GetMapping("/")
    @Operation(summary = "Obtener lista de usuarios")
    public Collection<Usuario> getUsers() {
        return usuarios.findAll();
    }

    @PostMapping("/")
    @Operation(summary = "Crear nuevo usuario")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario creado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Usuario.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos proporcionados invalidos",
                    content = @Content
            )
    })
    public ResponseEntity<UsuarioDto> createUser(@RequestBody Usuario user) {
        usuarios.save(user);
        UsuarioDto userdto = new UsuarioDto(user.getLogin(), user.getFullName(), user.getEntryDate(),
                                            user.getEstado(), user.getSaldo());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(userdto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar/modificar usuario")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario actualizado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Usuario.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Id proporcionado invalido",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<UsuarioDto> replaceUser(@PathVariable long id, @RequestBody Usuario newUser) {
        Optional<Usuario> user = usuarios.findById(id);
        if (user.isPresent()) {
            UsuarioDto userdto = new UsuarioDto(newUser.getLogin(), newUser.getFullName(), user.get().getEntryDate(),
                                                newUser.getEstado(), newUser.getSaldo());
            newUser.setId(id);
            usuarios.save(newUser);
            return ResponseEntity.ok(userdto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{login}/{password}")
    @Operation(summary = "Login de usuario")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Bienvenido al sistema",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Usuario.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Login proporcionado invalido",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado/contraseña incorrecta",
                    content = @Content
            )
    })
    public ResponseEntity<UsuarioDto> loginUser(@PathVariable String login, @PathVariable String password){
        Optional<Usuario> user = usuarios.findByLogin(login);
        if(user.isPresent()&&(user.get().getPassword().equals(password))) {
            UsuarioDto userdto = new UsuarioDto(user.get().getLogin(), user.get().getFullName(),
                                                user.get().getEntryDate(), user.get().getEstado(), user.get().getSaldo());
            return ResponseEntity.ok(userdto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario por su id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario eliminado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Usuario.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Id proporcionado invalido",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<UsuarioDto> deleteUser(@PathVariable long id) {
        Optional<Usuario> user = usuarios.findById(id);
        if (user.isPresent()) {
            user.get().setEstado(EstadoUsuario.INACTIVO);
            usuarios.save(user.get());
            UsuarioDto userdto = new UsuarioDto(user.get().getLogin(), user.get().getFullName(),
                    user.get().getEntryDate(), user.get().getEstado(), user.get().getSaldo());
            return ResponseEntity.ok(userdto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por su id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuario encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation=Usuario.class)
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Id proporcionado invalido",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<UsuarioDto> getUser(@PathVariable long id){
        Optional<Usuario> user = usuarios.findById(id);
        if(user.isPresent()) {
            UsuarioDto userdto = new UsuarioDto(user.get().getLogin(), user.get().getFullName(),
                    user.get().getEntryDate(), user.get().getEstado(), user.get().getSaldo());
            return ResponseEntity.ok(userdto);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/payment/{id}/{price}")
    @Operation(summary = "Realizar pago para reserva de bicicleta")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Pago realizado",
                    content = {@Content(
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Id proporcionado invalido",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Usuario inactivo (eliminado del sistema)",
                    content = @Content
            )
    })
    public ResponseEntity<UsuarioDto> payment(@PathVariable long id, @PathVariable double price) {
        Optional<Usuario> user = usuarios.findById(id);
        if (user.isPresent()) {
            if(user.get().getEstado()==EstadoUsuario.ACTIVO){
                if(user.get().getSaldo()>=price*2) {
                    user.get().setSaldo(user.get().getSaldo() - (price*2));
                    usuarios.save(user.get());
                    UsuarioDto userdto = new UsuarioDto(user.get().getLogin(), user.get().getFullName(),
                            user.get().getEntryDate(), user.get().getEstado(), user.get().getSaldo());
                    return ResponseEntity.ok(userdto);
                }else{
                    System.out.println("La bicicleta cuesta "+ price +", y el pago a realizar es de "+ price*2 +" por lo que no tiene suficiente dinero en la cuenta");
                    return ResponseEntity.unprocessableEntity().build();
                }
            }else{
                System.out.println("Usuario dado de baja");
                return ResponseEntity.unprocessableEntity().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/devolution/{id}/{price}")
    @Operation(summary = "Devolver fianza del alquiler una vez finalizado")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Fianza devuelta",
                    content = {@Content(
                            mediaType = "application/json"
                    )}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Id proporcionado invalido",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Usuario no encontrado",
                    content = @Content
            )
    })
    public ResponseEntity<UsuarioDto> devolution(@PathVariable long id, @PathVariable double price){
        Optional<Usuario> user = usuarios.findById(id);
        if ((user.isPresent())) {
            user.get().setSaldo(user.get().getSaldo() + price);
            usuarios.save(user.get());
            UsuarioDto userdto = new UsuarioDto(user.get().getLogin(), user.get().getFullName(),
                    user.get().getEntryDate(), user.get().getEstado(), user.get().getSaldo());
            return ResponseEntity.ok(userdto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
