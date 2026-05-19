package duoc.cl.OrderService.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Game-Catalog")
public interface GameCatalogClient {

    @GetMapping("/api/games/{id}/price")
    Double getGamePrice(@PathVariable("id") Long id);
}
