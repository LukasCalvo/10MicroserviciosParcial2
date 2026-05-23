package duoc.cl.InventoryService.Repository;

import duoc.cl.InventoryService.Model.GameKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameKeyRepository extends JpaRepository<GameKey, Long> {

    long countByJuegoIdAndVendidaFalse(long juegoId);
    Optional<GameKey> findFirstByJuegoIdAndVendidaFalse(long juegoId);
}