package duoc.cl.InventoryService.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GameKeyDTO {

    @NotBlank(message = "La clave del juego no puede estar vacía.")
    @Size(min = 10, max = 50, message = "La key debe tener entre 10 y 50 caracteres.")
    private String keyCode;

    @NotNull(message = "El ID del juego asociado es obligatorio.")
    private Integer juegoId;
}
