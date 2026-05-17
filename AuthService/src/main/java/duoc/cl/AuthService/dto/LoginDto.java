package duoc.cl.AuthService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotBlank(message = "El correo electronico es obligatorio.")
    @Email(message = "El formato del correo electronico no es valido.")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria.")
    private String password;
}