package duoc.cl.KeyDelivery.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "delivered_keys")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveredKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private Long gameId;

    @Column(nullable = false)
    private String clientEmail;

    @Column(nullable = false)
    private String digitalKey;

    private LocalDateTime deliveryDate;
}
