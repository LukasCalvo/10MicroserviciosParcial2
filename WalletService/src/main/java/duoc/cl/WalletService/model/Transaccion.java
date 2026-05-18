package duoc.cl.WalletService.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name="transacciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "billetera_id", nullable = false)
    private Billetera billetera;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private LocalDateTime fecha;
}