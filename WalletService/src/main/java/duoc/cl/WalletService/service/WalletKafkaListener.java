package duoc.cl.WalletService.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class WalletKafkaListener {

    @KafkaListener(topics = "key-sold-topic", groupId = "wallet-group")
    public void procesarVenta(String mensaje) {
        System.out.println("Mensaje: " + mensaje);
    }
}