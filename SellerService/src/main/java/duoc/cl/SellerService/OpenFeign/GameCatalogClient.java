package duoc.cl.SellerService.OpenFeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "game-catalog")
public interface GameCatalogClient {

    @GetMapping("/api/v1/games/exists/{id}")
    Boolean checkGameExists(@PathVariable("id") Long id);
}
