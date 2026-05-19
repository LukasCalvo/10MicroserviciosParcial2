package duoc.cl.SellerService.Repository;

import duoc.cl.SellerService.Entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByAuthId(Long authId);
}
