package duoc.cl.KeyDelivery.service;

import duoc.cl.KeyDelivery.client.InventoryClient;
import duoc.cl.KeyDelivery.domain.DeliveredKey;
import duoc.cl.KeyDelivery.dto.KeyDeliveryResponseDTO;
import duoc.cl.KeyDelivery.dto.PaymentConfirmedEvent;
import duoc.cl.KeyDelivery.repository.DeliveredKeyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeyDeliveryService {

    private final DeliveredKeyRepository repository;
    private final InventoryClient inventoryClient;

    @Transactional
    public void processKeyDelivery(PaymentConfirmedEvent event) {
        try {
            Map<String, Object> respuestaInventario = inventoryClient.claimGameKey(event.getGameId());
            String decryptedKey = (String) respuestaInventario.get("keyCode");

            DeliveredKey delivery = DeliveredKey.builder()
                    .orderId(event.getOrderId())
                    .gameId(event.getGameId())
                    .clientEmail(event.getClientEmail())
                    .digitalKey(decryptedKey)
                    .deliveryDate(LocalDateTime.now())
                    .build();

            repository.save(delivery);

            log.info("¡Key enviada con éxito al correo {} para la orden {}!", event.getClientEmail(), event.getOrderId());

        } catch (Exception e) {
            log.error("Error al procesar la entrega de la llave para la orden: {}", event.getOrderId(), e);
        }
    }

    public KeyDeliveryResponseDTO getDeliveryByOrder(Long orderId) {
        DeliveredKey deliveredKey = repository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("No se encontró ninguna Key liberada para la orden: " + orderId));

        return new KeyDeliveryResponseDTO(
                deliveredKey.getOrderId(),
                deliveredKey.getGameId(),
                deliveredKey.getDigitalKey(),
                deliveredKey.getDeliveryDate()
        );
    }
}
