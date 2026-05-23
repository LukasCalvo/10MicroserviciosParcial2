package duoc.cl.KeyDelivery.controller;

import duoc.cl.KeyDelivery.dto.KeyDeliveryResponseDTO;
import duoc.cl.KeyDelivery.service.KeyDeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/deliveries")
@RequiredArgsConstructor
public class KeyDeliveryController {

    private final KeyDeliveryService keyDeliveryService;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<KeyDeliveryResponseDTO> getDeliveryKeyByOrder(@PathVariable Long orderId) {
        KeyDeliveryResponseDTO response = keyDeliveryService.getDeliveryByOrder(orderId);
        return ResponseEntity.ok(response);
    }
}
