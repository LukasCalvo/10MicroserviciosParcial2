package duoc.cl.SellerService.Repository;

import duoc.cl.SellerService.Entity.SellerOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OfferRepository extends JpaRepository<SellerOffer, Long> {
    List<SellerOffer> findBySellerIdAndActiveTrue(Long sellerId);

    @Query("SELECT o FROM SellerOffer o WHERE o.gameId = :gameId AND o.active = true ORDER BY o.price ASC")
    List<SellerOffer> findBestOffersForGame(Long gameId);
}
