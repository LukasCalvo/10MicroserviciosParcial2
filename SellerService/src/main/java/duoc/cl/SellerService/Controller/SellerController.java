package duoc.cl.SellerService.Controller;

import duoc.cl.SellerService.DTO.*;
import duoc.cl.SellerService.Entity.*;
import duoc.cl.SellerService.Service.SellerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @PostMapping
    public ResponseEntity<Seller> registerSeller(@Valid @RequestBody SellerRequestDTO dto) {
        Seller newSeller = sellerService.createSeller(dto);
        return new ResponseEntity<>(newSeller, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/offers")
    public ResponseEntity<SellerOffer> createOffer(
            @PathVariable("id") Long sellerId,
            @Valid @RequestBody OfferRequestDTO dto) {
        SellerOffer newOffer = sellerService.createOffer(sellerId, dto);
        return new ResponseEntity<>(newOffer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/offers")
    public ResponseEntity<List<SellerOffer>> getSellerOffers(@PathVariable("id") Long sellerId) {
        List<SellerOffer> offers = sellerService.getActiveOffersBySeller(sellerId);
        return ResponseEntity.ok(offers);
    }
}
