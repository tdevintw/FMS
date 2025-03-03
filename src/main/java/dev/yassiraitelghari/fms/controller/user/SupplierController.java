package dev.yassiraitelghari.fms.controller.user;

import dev.yassiraitelghari.fms.dto.request.order.OrderCreateDTO;
import dev.yassiraitelghari.fms.dto.request.order.OrderUpdateDTO;
import dev.yassiraitelghari.fms.dto.request.user.UserUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.order.OrderDTO;
import dev.yassiraitelghari.fms.dto.response.order.OrderDetailDTO;
import dev.yassiraitelghari.fms.service.user.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return ResponseEntity.status(200).body(supplierService.findById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(200).body(supplierService.getAll());
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UserUpdateDTO user, @PathVariable UUID id) {
        return ResponseEntity.status(200).body("Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        supplierService.delete(id);
        return ResponseEntity.status(200).body("Supplier was deleted");
    }
}
