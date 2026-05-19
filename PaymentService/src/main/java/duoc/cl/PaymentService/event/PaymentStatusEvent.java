package duoc.cl.PaymentService.event;

import java.math.BigDecimal;

public record PaymentStatusEvent(
        String orderId,
        String customerId,
        BigDecimal amount,
        String status,
        String gatewayTransactionId
) {}
