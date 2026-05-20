package duoc.cl.InventoryService.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "game-catalog", path = "/api/v1/juegos")
public interface CatalogClient {
    @GetMapping("/validate/{id}")
    boolean verificarSiJuegoExiste(@PathVariable("id") Integer id);
}
