package duoc.cl.KeyDelivery.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Map;

@FeignClient(name = "inventory-service", path = "/api/v1/inventory")
public interface InventoryClient {

    @PostMapping("/dispatch/game/{gameId}")
    Map<String, Object> claimGameKey(@PathVariable("gameId") Long gameId);
}