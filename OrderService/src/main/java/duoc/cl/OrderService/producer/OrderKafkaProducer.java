package duoc.cl.OrderService.producer;

import duoc.cl.OrderService.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "order-created-topic";

    public void sendOrderEvent(OrderCreatedEvent event) {
        kafkaTemplate.send(TOPIC, event);
    }
}
