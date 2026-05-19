package duoc.cl.KeyDelivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentConfirmedEvent {
    @NotNull(message = "El ID de orden es obligatorio")
    private Long orderId;

    @NotNull(message = "El ID de juego es obligatorio")
    private Long gameId;

    @NotBlank(message = "El correo del cliente es obligatorio")
    private String clientEmail;
}
