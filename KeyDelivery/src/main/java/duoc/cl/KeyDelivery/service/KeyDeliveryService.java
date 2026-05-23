package duoc.cl.KeyDelivery.service;

import duoc.cl.KeyDelivery.client.InventoryClient;
import duoc.cl.KeyDelivery.domain.DeliveredKey;
import duoc.cl.KeyDelivery.dto.KeyDeliveryResponseDTO;
import duoc.cl.KeyDelivery.dto.PaymentStatusEvent; // <-- IMPORTAMOS LA NUEVA CLASE
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
    public void processKeyDelivery(PaymentStatusEvent event) {
        try {
            log.info("GameId recibido del evento: {}", event.getGameId());

            Long numericOrderId = Long.parseLong(event.getOrderId().replace("ORD-", ""));

            Map<String, Object> respuestaInventario = inventoryClient.claimGameKey(event.getGameId());
            String decryptedKey = (String) respuestaInventario.get("keyCode");

            DeliveredKey delivery = DeliveredKey.builder()
                    .orderId(numericOrderId)
                    .gameId(event.getGameId())
                    .clientEmail(event.getCustomerId())
                    .digitalKey(decryptedKey)
                    .deliveryDate(LocalDateTime.now())
                    .build();

            repository.save(delivery);

            log.info("Key enviada con exito al correo {} para la orden {}", event.getCustomerId(), event.getOrderId());

        } catch (Exception e) {
            log.error("Error al procesar la entrega de la llave para la orden: {}", event.getOrderId(), e);
        }
    }

    public KeyDeliveryResponseDTO getDeliveryByOrder(Long orderId) {
        DeliveredKey deliveredKey = repository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("No se encontro ninguna Key liberada para la orden: " + orderId));

        return new KeyDeliveryResponseDTO(
                deliveredKey.getOrderId(),
                deliveredKey.getGameId(),
                deliveredKey.getDigitalKey(),
                deliveredKey.getDeliveryDate()
        );
    }
}