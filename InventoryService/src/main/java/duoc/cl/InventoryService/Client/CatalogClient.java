package duoc.cl.InventoryService.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "catalog-service", path = "/api/v1/games")
public interface CatalogClient {

    @GetMapping("/validate/{id}")
    boolean verificarSiJuegoExiste(@PathVariable("id") Long id);
}
