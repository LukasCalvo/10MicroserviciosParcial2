package duoc.cl.SellerService.Service;

import duoc.cl.SellerService.DTO.*;
import duoc.cl.SellerService.Entity.*;
import duoc.cl.SellerService.OpenFeign.GameCatalogClient;
import duoc.cl.SellerService.Kafka.OfferEventProducer;
import duoc.cl.SellerService.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerService {

    private final SellerRepository sellerRepository;
    private final OfferRepository offerRepository;
    private final GameCatalogClient gameCatalogClient;
    private final OfferEventProducer offerEventProducer;

    @Transactional
    public Seller createSeller(SellerRequestDTO dto) {
        Seller seller = new Seller();
        seller.setAuthId(dto.getAuthId());
        seller.setStoreName(dto.getStoreName());
        seller.setDescription(dto.getDescription());
        return sellerRepository.save(seller);
    }

    @Transactional
    public SellerOffer createOffer(Long sellerId, OfferRequestDTO dto) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new RuntimeException("Vendedor no encontrado"));

        Boolean gameExists = gameCatalogClient.checkGameExists(dto.getGameId());
        if (Boolean.FALSE.equals(gameExists)) {
            throw new IllegalArgumentException("El ID del videojuego no es válido en el catálogo maestro.");
        }

        SellerOffer offer = new SellerOffer();
        offer.setSeller(seller);
        offer.setGameId(dto.getGameId());
        offer.setPrice(dto.getPrice());
        offer.setStock(dto.getStock());

        SellerOffer savedOffer = offerRepository.save(offer);

        offerEventProducer.publishOfferUpdate(savedOffer.getId(), savedOffer.getGameId(), savedOffer.getPrice(), savedOffer.getStock());

        return savedOffer;
    }

    public List<SellerOffer> getActiveOffersBySeller(Long sellerId) {
        return offerRepository.findBySellerIdAndActiveTrue(sellerId);
    }
}