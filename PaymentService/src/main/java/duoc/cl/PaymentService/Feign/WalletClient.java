package duoc.cl.PaymentService.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.math.BigDecimal;

@FeignClient(name = "wallet-service", url = "http://localhost:8089/api/wallets")
public interface WalletClient {

    @GetMapping("/validate-balance")
    boolean verifyFunds(@RequestParam String customerId, @RequestParam BigDecimal amount);
}
