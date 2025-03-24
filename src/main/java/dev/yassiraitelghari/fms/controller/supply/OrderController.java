package dev.yassiraitelghari.fms.controller.supply;

import dev.yassiraitelghari.fms.dto.request.order.OrderCreateDTO;
import dev.yassiraitelghari.fms.dto.request.order.OrderLocationDTO;
import dev.yassiraitelghari.fms.dto.request.order.OrderUpdateDTO;
import dev.yassiraitelghari.fms.dto.request.user.AssignShipperDTO;
import dev.yassiraitelghari.fms.dto.response.order.OrderDTO;
import dev.yassiraitelghari.fms.dto.response.order.OrderDetailDTO;

import dev.yassiraitelghari.fms.service.supply.OrderService;
import jakarta.validation.Valid;
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

    @GetMapping("/manager/{id}")
    public ResponseEntity<?> getAllOfAManager(@PathVariable UUID id) {
        List<OrderDetailDTO> orders = orderService.getAllOfAManager(id);
        return ResponseEntity.status(200).body(orders);
    }

    @GetMapping("/shipper/{id}")
    public ResponseEntity<?> getAllOfAShipper(@PathVariable UUID id) {
        List<OrderDetailDTO> orders = orderService.getAllOfAShipper(id);
        return ResponseEntity.status(200).body(orders);
    }




    @GetMapping("/supplier/{id}")
    public ResponseEntity<?> getAllOfASupplier(@PathVariable UUID id) {
        List<OrderDetailDTO> orders = orderService.getOrdersOfASupplier(id);
        return ResponseEntity.status(200).body(orders);
    }




    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','SUPPLIER')")
    @PostMapping
    public ResponseEntity<?> add(@Valid  @RequestBody OrderCreateDTO order) {
        return ResponseEntity.status(201).body(orderService.add(order));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','SUPPLIER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody OrderUpdateDTO order, @PathVariable UUID id) {
        return ResponseEntity.status(200).body(orderService.edit(id , order));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','SUPPLIER')")
    @PutMapping("/assign-shipper")
    public ResponseEntity<?> assignShipper(@RequestBody AssignShipperDTO assignShipperDTO) {
        return ResponseEntity.status(200).body(orderService.assignShipper(assignShipperDTO.getOrderId() , assignShipperDTO.getShipperId()));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','SUPPLIER','SHIPPER')")
    @PutMapping("/set-location/{id}")
    public ResponseEntity<?> updateCurrentLocation(@RequestBody OrderLocationDTO orderLocationDTO , @PathVariable UUID id) {
        return ResponseEntity.status(200).body(orderService.setCurrentLocation(id , orderLocationDTO.getCurrentLocation()));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','SUPPLIER','SHIPPER')")
    @PutMapping("/set-status-delivered/{id}")
    public ResponseEntity<?> setStatusToDelivered( @PathVariable UUID id) {
        return ResponseEntity.status(200).body(orderService.setStatusToDelivered(id));
    }



    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','SUPPLIER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        orderService.delete(id);
        return ResponseEntity.status(200).body("order Was deleted");
    }


}
