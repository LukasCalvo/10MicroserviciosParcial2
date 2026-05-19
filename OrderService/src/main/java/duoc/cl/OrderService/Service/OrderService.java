package duoc.cl.OrderService.Service;

import duoc.cl.OrderService.Client.GameCatalogClient;
import duoc.cl.OrderService.dto.OrderRequestDTO;
import duoc.cl.OrderService.event.OrderCreatedEvent;
import duoc.cl.OrderService.Model.Order;
import duoc.cl.OrderService.producer.OrderKafkaProducer;
import duoc.cl.OrderService.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final GameCatalogClient gameCatalogClient;
    private final OrderKafkaProducer orderKafkaProducer;

    public Order createOrder(OrderRequestDTO orderRequest) {
        Double gamePrice = gameCatalogClient.getGamePrice(orderRequest.gameId());
        Double totalAmount = gamePrice * orderRequest.quantity();

        Order order = Order.builder()
                .userId(orderRequest.userId())
                .gameId(orderRequest.gameId())
                .quantity(orderRequest.quantity())
                .totalAmount(totalAmount)
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);

        OrderCreatedEvent event = new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getUserId(),
                savedOrder.getGameId(),
                savedOrder.getTotalAmount()
        );
        orderKafkaProducer.sendOrderEvent(event);

        return savedOrder;
    }
}
