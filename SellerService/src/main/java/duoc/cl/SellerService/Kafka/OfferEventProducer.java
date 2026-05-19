package duoc.cl.SellerService.Kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OfferEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private static final String TOPIC = "seller-offers-topic";

    public void publishOfferUpdate(Long offerId, Long gameId, Double price, Integer stock) {
        String message = String.format("{\"offerId\":%d, \"gameId\":%d, \"price\":%.2f, \"stock\":%d}",
                offerId, gameId, price, stock);
        kafkaTemplate.send(TOPIC, message);
    }
}
