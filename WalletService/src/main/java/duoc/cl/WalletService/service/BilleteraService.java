package duoc.cl.WalletService.service;

import duoc.cl.WalletService.dto.TransaccionDto;
import duoc.cl.WalletService.model.Billetera;
import duoc.cl.WalletService.model.Transaccion;
import duoc.cl.WalletService.repository.BilleteraRepository;
import duoc.cl.WalletService.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class BilleteraService {

    @Autowired
    private BilleteraRepository billeteraRepository;

    @Autowired
    private TransaccionRepository transaccionRepository;

    public Billetera obtenerBilletera(String email) {
        return billeteraRepository.findByUsuarioEmail(email).orElseGet(() -> {
            Billetera nueva = new Billetera();
            nueva.setUsuarioEmail(email);
            nueva.setSaldo(0.0);
            return billeteraRepository.save(nueva);
        });
    }

    @Transactional
    public Billetera procesarTransaccion(TransaccionDto transaccionDto) {
        Billetera billetera = obtenerBilletera(transaccionDto.getUsuarioEmail());
        String tipo = transaccionDto.getTipo().toUpperCase();

        if (tipo.equals("RECARGA") || tipo.equals("VENTA")) {
            billetera.setSaldo(billetera.getSaldo() + transaccionDto.getMonto());
        } else if (tipo.equals("COMPRA")) {
            if (billetera.getSaldo() < transaccionDto.getMonto()) {
                throw new RuntimeException("Saldo insuficiente para realizar la transacción.");
            }
            billetera.setSaldo(billetera.getSaldo() - transaccionDto.getMonto());
        } else {
            throw new RuntimeException("Tipo de transacción no válido.");
        }

        Billetera billeteraActualizada = billeteraRepository.save(billetera);

        Transaccion transaccion = new Transaccion();
        transaccion.setBilletera(billeteraActualizada);
        transaccion.setMonto(transaccionDto.getMonto());
        transaccion.setTipo(tipo);
        transaccion.setFecha(LocalDateTime.now());
        transaccionRepository.save(transaccion);

        return billeteraActualizada;
    }
}