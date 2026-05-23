package duoc.cl.GameCatalog.controller;

import duoc.cl.GameCatalog.dto.JuegoDto;
import duoc.cl.GameCatalog.model.Juego;
import duoc.cl.GameCatalog.service.JuegoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/juegos")
public class JuegoController {

    @Autowired
    private JuegoService juegoService;

    @GetMapping
    public ResponseEntity<List<Juego>> listarJuegos() {
        return ResponseEntity.ok(juegoService.getJuegos());
    }

    @PostMapping
    public ResponseEntity<Juego> crearJuego(@Valid @RequestBody JuegoDto juegoDTO) {
        Juego juego = new Juego();
        juego.setNombre(juegoDTO.getNombre());
        juego.setDescripcion(juegoDTO.getDescripcion());
        juego.setGenero(juegoDTO.getGenero());
        juego.setPrecio(juegoDTO.getPrecio());

        Juego juegoCreado = juegoService.saveJuego(juego);
        return ResponseEntity.status(HttpStatus.CREATED).body(juegoCreado);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Juego> obtenerPorId(@PathVariable Long id) {
        Juego juego = juegoService.getJuegoById(id);
        if (juego != null) {
            return ResponseEntity.ok(juego);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/validate/{id}")
    public boolean verificarSiJuegoExiste(@PathVariable Long id) {
        return juegoService.getJuegoById(id) != null;
    }

    @GetMapping("/{id}/price")
    public ResponseEntity<Double> obtenerPrecio(@PathVariable Long id) {
        Juego juego = juegoService.getJuegoById(id);
        if (juego != null) {
            return ResponseEntity.ok(juego.getPrecio());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Juego> actualizarJuego(@PathVariable Long id, @Valid @RequestBody JuegoDto juegoDTO) {
        Juego juegoParaActualizar = new Juego();
        juegoParaActualizar.setNombre(juegoDTO.getNombre());
        juegoParaActualizar.setDescripcion(juegoDTO.getDescripcion());
        juegoParaActualizar.setGenero(juegoDTO.getGenero());
        juegoParaActualizar.setPrecio(juegoDTO.getPrecio());

        Juego juegoActualizado = juegoService.updateJuego(id, juegoParaActualizar);

        if (juegoActualizado != null) {
            return ResponseEntity.ok(juegoActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarJuego(@PathVariable Long id) {
        boolean eliminado = juegoService.deleteJuego(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}