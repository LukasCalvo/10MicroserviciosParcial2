package duoc.cl.SupportService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    @NotBlank(message = "El email es obligatorio.")
    @Email(message = "Formato de email invalido.")
    private String usuarioEmail;

    @NotBlank(message = "El asunto no puede estar vacio.")
    @Size(max = 100, message = "El asunto es demasiado largo.")
    private String asunto;

    @NotBlank(message = "Debes describir tu problema.")
    @Size(max = 1000, message = "La descripcion no puede exceder los 1000 caracteres.")
    private String descripcion;
}