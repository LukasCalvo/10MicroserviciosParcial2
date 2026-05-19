package duoc.cl.SellerService.DTO;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SellerRequestDTO {
    @NotNull(message = "El ID de autenticación es obligatorio")
    private Long authId;

    @NotBlank(message = "El nombre de la tienda no puede estar vacío")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String storeName;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String description;
}
