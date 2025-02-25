package dev.yassiraitelghari.fms.controller.supply;

import dev.yassiraitelghari.fms.dto.request.order.OrderCreateDTO;
import dev.yassiraitelghari.fms.dto.request.order.OrderUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.order.OrderDTO;
import dev.yassiraitelghari.fms.dto.response.order.OrderDetailDTO;

import dev.yassiraitelghari.fms.service.supply.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        OrderDTO order = orderService.findById(id);
        return ResponseEntity.status(200).body(order);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<OrderDetailDTO> orders = orderService.getAll();
        return ResponseEntity.status(200).body(orders);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','SUPPLIER')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody OrderCreateDTO order) {
        return ResponseEntity.status(201).body(orderService.add(order));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','SUPPLIER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody OrderUpdateDTO order, @PathVariable UUID id) {
        return ResponseEntity.status(201).body(orderService.edit(id , order));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','SUPPLIER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        orderService.delete(id);
        return ResponseEntity.status(200).body("order Was deleted");
    }


}
