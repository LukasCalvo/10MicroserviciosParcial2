package duoc.cl.SellerService.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "seller_offers")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class SellerOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long gameId;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer stock;

    private Boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller;
}
