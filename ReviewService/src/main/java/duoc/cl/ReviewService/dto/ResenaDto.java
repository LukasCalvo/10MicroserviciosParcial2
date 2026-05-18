package duoc.cl.ReviewService.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResenaDto {

    @NotNull(message = "El ID del juego es obligatorio.")
    private Integer juegoId;

    @NotBlank(message = "El email del usuario es obligatorio.")
    @Email(message = "Formato de email invalido.")
    private String usuarioEmail;

    @Min(value = 1, message = "El puntaje minimo es 1 estrella.")
    @Max(value = 5, message = "El puntaje maximo es 5 estrellas.")
    private int puntaje;

    @Size(max = 500, message = "El comentario no puede exceder los 500 caracteres.")
    private String comentario;
}