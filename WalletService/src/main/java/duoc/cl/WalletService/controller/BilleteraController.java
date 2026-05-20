package duoc.cl.WalletService.controller;

import duoc.cl.WalletService.dto.TransaccionDto;
import duoc.cl.WalletService.model.Billetera;
import duoc.cl.WalletService.service.BilleteraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/wallet")
public class BilleteraController {

    @Autowired
    private BilleteraService billeteraService;

    @GetMapping("/{email}")
    public ResponseEntity<Billetera> verSaldo(@PathVariable String email) {
        return ResponseEntity.ok(billeteraService.obtenerBilletera(email));
    }

    @GetMapping("/validate-balance")
    public boolean verifyFunds(@RequestParam String customerId, @RequestParam Double amount) {
        Billetera billetera = billeteraService.obtenerBilletera(customerId);
        return billetera.getSaldo() >= amount;
    }

    @PostMapping("/transaccion")
    public ResponseEntity<?> procesarTransaccion(@Valid @RequestBody TransaccionDto transaccion) {
        try {
            Billetera actualizada = billeteraService.procesarTransaccion(transaccion);
            return ResponseEntity.ok(actualizada);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
}