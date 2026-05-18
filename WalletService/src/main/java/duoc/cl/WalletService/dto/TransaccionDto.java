package duoc.cl.WalletService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionDto {

    @NotBlank(message = "El email del usuario es obligatorio.")
    @Email(message = "Formato de email invalido.")
    private String usuarioEmail;

    @NotNull(message = "El monto es obligatorio.")
    @Positive(message = "El monto debe ser mayor a cero.")
    private Double monto;

    @NotBlank(message = "El tipo de operación es obligatorio (RECARGA, COMPRA, VENTA).")
    private String tipo;
}