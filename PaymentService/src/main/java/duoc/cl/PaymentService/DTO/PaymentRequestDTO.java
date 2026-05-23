package duoc.cl.PaymentService.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record PaymentRequestDTO(
        @NotNull(message = "El ID de la orden es obligatorio")
        String orderId,

        @NotNull(message = "El ID del cliente es obligatorio")
        String customerId,

        @NotNull(message = "El monto es obligatorio")
        @Positive(message = "El monto debe ser mayor a cero")
        BigDecimal amount,

        @NotNull(message = "El método de pago es obligatorio")
        String paymentMethod,

        @NotNull(message = "La id del juego no puede estar vacia")
        Long juegoId
) {}
