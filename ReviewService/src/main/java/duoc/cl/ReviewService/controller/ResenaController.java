package duoc.cl.ReviewService.controller;

import duoc.cl.ReviewService.dto.ResenaDto;
import duoc.cl.ReviewService.model.Resena;
import duoc.cl.ReviewService.service.ResenaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @PostMapping
    public ResponseEntity<Resena> agregarResena(@Valid @RequestBody ResenaDto dto) {
        Resena nueva = resenaService.crearResena(dto);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @GetMapping("/juego/{juegoId}")
    public ResponseEntity<List<Resena>> verResenasDeJuego(@PathVariable int juegoId) {
        return ResponseEntity.ok(resenaService.obtenerResenasPorJuego(juegoId));
    }

    @GetMapping("/juego/{juegoId}/promedio")
    public ResponseEntity<Double> verPromedioDeJuego(@PathVariable int juegoId) {
        return ResponseEntity.ok(resenaService.obtenerPromedioJuego(juegoId));
    }
}