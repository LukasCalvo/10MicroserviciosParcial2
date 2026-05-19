package duoc.cl.SellerService.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class OfferRequestDTO {
    @NotNull(message = "El ID del videojuego es obligatorio")
    private Long gameId;

    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero(message = "El precio debe ser un valor positivo o cero")
    private Double price;

    @NotNull(message = "El stock inicial es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;
}
