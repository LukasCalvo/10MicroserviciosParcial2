package duoc.cl.WalletService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="billeteras")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Billetera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String usuarioEmail;

    @Column(nullable = false)
    private Double saldo;
}