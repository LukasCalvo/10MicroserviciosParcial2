package duoc.cl.InventoryService.Controller;

import duoc.cl.InventoryService.dto.GameKeyDTO;
import duoc.cl.InventoryService.Model.GameKey;
import duoc.cl.InventoryService.Service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<GameKey> agregarKeyAlInventario(@Valid @RequestBody GameKeyDTO dto) {
        GameKey creada = service.registrarKey(dto);
        return new ResponseEntity<>(creada, HttpStatus.CREATED);
    }

    @GetMapping("/stock/game/{juegoId}")
    public ResponseEntity<Long> consultarStock(@PathVariable long juegoId) {
        long stock = service.obtenerStockDisponible(juegoId);
        return ResponseEntity.ok(stock);
    }

    @PostMapping("/dispatch/game/{juegoId}")
    public ResponseEntity<GameKey> despacharKeyJuego(@PathVariable long juegoId) {
        GameKey keyDespachada = service.despacharKey(juegoId);
        return ResponseEntity.ok(keyDespachada);
    }
}
