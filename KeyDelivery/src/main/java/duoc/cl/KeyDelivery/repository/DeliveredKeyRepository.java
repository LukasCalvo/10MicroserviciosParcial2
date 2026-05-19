package duoc.cl.KeyDelivery.repository;

import duoc.cl.KeyDelivery.domain.DeliveredKey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DeliveredKeyRepository extends JpaRepository<DeliveredKey, Long> {
    Optional<DeliveredKey> findByOrderId(Long orderId);
}
