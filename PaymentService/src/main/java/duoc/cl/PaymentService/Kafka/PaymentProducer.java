package duoc.cl.PaymentService.Kafka;

import duoc.cl.PaymentService.event.PaymentStatusEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentProducer {

    private final KafkaTemplate<String, PaymentStatusEvent> kafkaTemplate;
    private static final String TOPIC = "payment-confirmed";

    public void sendPaymentEvent(PaymentStatusEvent event) {
        log.info("Publicando evento de pago en Kafka para la Orden: {}", event.orderId());
        CompletableFuture<SendResult<String, PaymentStatusEvent>> send = this.kafkaTemplate.send(TOPIC, event.orderId(), event);
    }
}