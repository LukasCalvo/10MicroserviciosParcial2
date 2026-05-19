package duoc.cl.KeyDelivery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class KeyDeliveryResponseDTO {
    private Long orderId;
    private Long gameId;
    private String digitalKey;
    private LocalDateTime deliveryDate;
}
