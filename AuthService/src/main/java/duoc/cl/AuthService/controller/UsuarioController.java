package duoc.cl.AuthService.controller;

import duoc.cl.AuthService.dto.LoginDto;
import duoc.cl.AuthService.dto.RegistroDto;
import duoc.cl.AuthService.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    @PostMapping("/register")
    public ResponseEntity<?> registrar(@Valid @RequestBody RegistroDto registroDto) {
        try {
            String respuesta = usuarioService.registrarUsuario(registroDto);
            Map<String, String> exito = new HashMap<>();
            exito.put("mensaje", respuesta);
            return new ResponseEntity<>(exito, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto) {
        try {
            String token = usuarioService.login(loginDto);
            Map<String, String> respuestaToken = new HashMap<>();
            respuestaToken.put("token", token);
            return new ResponseEntity<>(respuestaToken, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
    }
}