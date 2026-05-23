package duoc.cl.OrderService.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "game-catalog", path = "/api/v1/juegos")
public interface GameCatalogClient {
    @GetMapping("/{id}/price")
    Double getGamePrice(@PathVariable("id") Long id);
}