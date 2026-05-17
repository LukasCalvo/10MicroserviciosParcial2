package duoc.cl.GameCatalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JuegoDto {

    @NotBlank(message = "El nombre del juego no puede estar en blanco.")
    @Size(min = 1, max = 100, message = "El nombre debe tener entre 1 y 100 caracteres.")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede tener mas de 500 caracteres.")
    private String descripcion;

    @NotBlank(message = "El genero del juego es obligatorio.")
    private String genero;

    @NotNull(message = "El precio no puede ser nulo.")
    @Positive(message = "El precio debe ser un numero positivo.")
    private Double precio;
}