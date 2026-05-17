package duoc.cl.AuthService.service;

import duoc.cl.AuthService.dto.RegistroDto;
import duoc.cl.AuthService.dto.LoginDto;
import duoc.cl.AuthService.model.Usuario;
import duoc.cl.AuthService.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final String SECRET_KEY_STR = "VGhpcy1Jcy1BLVZlcnktU2VjdXJlLVNlY3JldC1LZXktRm9yLUpXVC1BcGktQXV0aGVudGljYXRpb24=";

    public String registrarUsuario(RegistroDto registroDto) {
        if (usuarioRepository.existsByEmail(registroDto.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está registrado.");
        }
        if (usuarioRepository.existsByUsername(registroDto.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya está tomado.");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(registroDto.getUsername());
        usuario.setEmail(registroDto.getEmail());
        usuario.setRol(registroDto.getRol().toUpperCase());

        String passwordCifrada = passwordEncoder.encode(registroDto.getPassword());
        usuario.setPassword(passwordCifrada);

        usuarioRepository.save(usuario);
        return "Usuario registrado exitosamente.";
    }

    public String login(LoginDto loginDto) {
        Usuario usuario = usuarioRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales incorrectas (Usuario no encontrado)."));

        if (!passwordEncoder.matches(loginDto.getPassword(), usuario.getPassword())) {
            throw new RuntimeException("Credenciales incorrectas (Contraseña inválida).");
        }

        return generarToken(usuario);
    }

    private String generarToken(Usuario usuario) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY_STR.getBytes(StandardCharsets.UTF_8));

        long tiempoExpiracion = 86400000;
        Date fechaExpiracion = new Date(System.currentTimeMillis() + tiempoExpiracion);

        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("username", usuario.getUsername())
                .claim("rol", usuario.getRol())
                .issuedAt(new Date())
                .expiration(fechaExpiracion)
                .signWith(key)
                .compact();
    }
}