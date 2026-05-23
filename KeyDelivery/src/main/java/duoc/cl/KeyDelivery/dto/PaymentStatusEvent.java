package duoc.cl.KeyDelivery.dto;

import lombok.Data;

@Data
public class PaymentStatusEvent {
    private Long id;
    private String orderId;
    private String customerId;
    private Double amount;
    private String paymentMethod;
    private String status;
    private String gatewayTransactionId;
    private String createdAt;
    private Long gameId;
}