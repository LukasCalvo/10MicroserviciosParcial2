package duoc.cl.OrderService.Controller;

import duoc.cl.OrderService.dto.OrderRequestDTO;
import duoc.cl.OrderService.Model.Order;
import duoc.cl.OrderService.Service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody OrderRequestDTO orderRequestDTO) {
        Order processingOrder = orderService.createOrder(orderRequestDTO);
        return new ResponseEntity<>(processingOrder, HttpStatus.CREATED);
    }
}
