package duoc.cl.OrderService.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record OrderRequestDTO(
        @NotNull(message = "El ID de usuario no puede ser nulo")
        Long userId,

        @NotNull(message = "El ID del videojuego no puede ser nulo")
        Long gameId,

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad mínima de compra es 1")
        Integer quantity
) {}
