package duoc.cl.PaymentService.Controller;

import duoc.cl.PaymentService.DTO.PaymentRequestDTO;
import duoc.cl.PaymentService.Entity.PaymentTransaction;
import duoc.cl.PaymentService.Service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<PaymentTransaction> executePayment(@Valid @RequestBody PaymentRequestDTO paymentRequest) {
        PaymentTransaction result = paymentService.processPayment(paymentRequest);
        if ("REJECTED".equals(result.getStatus())) {
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
