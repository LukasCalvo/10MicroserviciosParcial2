package duoc.cl.SellerService.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "sellers")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long authId;

    @Column(nullable = false, length = 100)
    private String storeName;

    @Column(length = 500)
    private String description;

    private Double reputation = 5.0;

    private Boolean active = true;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SellerOffer> offers;
}