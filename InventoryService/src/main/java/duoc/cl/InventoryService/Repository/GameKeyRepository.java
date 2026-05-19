package duoc.cl.InventoryService.Repository;

import duoc.cl.InventoryService.Model.GameKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameKeyRepository extends JpaRepository<GameKey, Long> {

    long countByJuegoIdAndVendidaFalse(long juegoId);

    @Query("SELECT gk FROM GameKey gk WHERE gk.juegoId = :juegoId AND gk.vendida = false")
    Optional<GameKey> findFirstAvailableKey(@Param("juegoId") long juegoId);
}
