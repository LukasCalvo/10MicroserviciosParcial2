package duoc.cl.InventoryService.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_keys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String keyCode;

    @Column(nullable = false)
    private int juegoId;

    @Column(nullable = false)
    private boolean vendida = false;
}
