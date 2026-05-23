package duoc.cl.PaymentService.Service;

import duoc.cl.PaymentService.DTO.PaymentRequestDTO;
import duoc.cl.PaymentService.Entity.PaymentTransaction;
import duoc.cl.PaymentService.Kafka.PaymentProducer;
import duoc.cl.PaymentService.event.PaymentStatusEvent;
import duoc.cl.PaymentService.Repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentProducer paymentProducer;


    @Transactional
    public PaymentTransaction processPayment(PaymentRequestDTO request) {

        String status = "APPROVED";
        String gatewayId = "TX-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        if (request.amount().doubleValue() < 500.0) {
            status = "REJECTED";
            gatewayId = null;
        }

        PaymentTransaction transaction = PaymentTransaction.builder()
                .orderId(request.orderId())
                .customerId(request.customerId())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .juegoId(request.juegoId())
                .status(status)
                .gatewayTransactionId(gatewayId)
                .createdAt(LocalDateTime.now())
                .build();

        PaymentTransaction savedTransaction = paymentRepository.save(transaction);

        PaymentStatusEvent event = new PaymentStatusEvent(
                savedTransaction.getOrderId(),
                savedTransaction.getCustomerId(),
                savedTransaction.getAmount(),
                savedTransaction.getStatus(),
                savedTransaction.getGatewayTransactionId(),
                savedTransaction.getJuegoId()
        );
        paymentProducer.sendPaymentEvent(event);

        return savedTransaction;
    }
}
