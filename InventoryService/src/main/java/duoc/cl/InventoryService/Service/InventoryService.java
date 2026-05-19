package duoc.cl.InventoryService.Service;

import duoc.cl.InventoryService.Client.CatalogClient;
import duoc.cl.InventoryService.dto.GameKeyDTO;
import duoc.cl.InventoryService.Model.GameKey;
import duoc.cl.InventoryService.Repository.GameKeyRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    private final GameKeyRepository repository;
    private final CatalogClient catalogClient;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public InventoryService(GameKeyRepository repository, CatalogClient catalogClient, KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.catalogClient = catalogClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public GameKey registrarKey(GameKeyDTO dto) {
        boolean existeJuego = catalogClient.verificarSiJuegoExiste(dto.getJuegoId());
        if (!existeJuego) {
            throw new IllegalArgumentException("El juego ID " + dto.getJuegoId() + " no existe en el catálogo.");
        }

        GameKey nuevaKey = GameKey.builder()
                .keyCode(dto.getKeyCode())
                .juegoId(Math.toIntExact(dto.getJuegoId()))
                .vendida(false)
                .build();

        GameKey guardada = repository.save(nuevaKey);

        kafkaTemplate.send("inventory-stock-updated", "JuegoID: " + dto.getJuegoId() + " - Stock Incrementado");

        return guardada;
    }

    public long obtenerStockDisponible(long juegoId) {
        return repository.countByJuegoIdAndVendidaFalse(juegoId);
    }

    @Transactional
    public GameKey despacharKey(long juegoId) {
        GameKey keyParaVender = repository.findFirstAvailableKey(juegoId)
                .orElseThrow(() -> new RuntimeException("No quedan Keys disponibles para el juego ID: " + juegoId));

        keyParaVender.setVendida(true);
        repository.save(keyParaVender);

        kafkaTemplate.send("key-sold-topic", "Key ID: " + keyParaVender.getId() + " despachada para juego: " + juegoId);

        return keyParaVender;
    }
}
