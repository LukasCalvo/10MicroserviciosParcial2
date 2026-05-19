package duoc.cl.KeyDelivery.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

    @PostMapping("/api/inventory/keys/claim/{gameId}")
    String claimGameKey(@PathVariable("gameId") Long gameId);
}
