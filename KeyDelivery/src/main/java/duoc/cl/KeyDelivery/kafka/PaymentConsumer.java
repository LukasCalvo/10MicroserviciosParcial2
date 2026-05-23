package duoc.cl.KeyDelivery.kafka;

import duoc.cl.KeyDelivery.dto.PaymentStatusEvent; // 1. Cambiamos la importación
import duoc.cl.KeyDelivery.service.KeyDeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentConsumer {

    private final KeyDeliveryService keyDeliveryService;

    @KafkaListener(topics = "payment-confirmed", groupId = "key-delivery-group-v2")
    public void listenPaymentConfirmation(@NonNull PaymentStatusEvent event) {
        log.info("Evento de pago recibido para la orden: {}", event.getOrderId());
        keyDeliveryService.processKeyDelivery(event);
    }
}