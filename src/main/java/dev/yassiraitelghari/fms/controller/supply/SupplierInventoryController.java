package dev.yassiraitelghari.fms.controller.supply;

import dev.yassiraitelghari.fms.dto.request.supplierInventory.SupplierInventoryCreateDTO;
import dev.yassiraitelghari.fms.dto.request.supplierInventory.SupplierInventoryUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.supplierInventory.SupplierInventoryDetailDTO;
import dev.yassiraitelghari.fms.service.supply.SupplierInventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/supplierInventories")
public class SupplierInventoryController {
    private final SupplierInventoryService supplierInventoryService;

    public SupplierInventoryController(SupplierInventoryService supplierInventoryService) {
        this.supplierInventoryService = supplierInventoryService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        SupplierInventoryDetailDTO supplierInventory = supplierInventoryService.findById(id);
        return ResponseEntity.status(200).body(supplierInventory);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<SupplierInventoryDetailDTO> supplierInventories = supplierInventoryService.getAll();
        return ResponseEntity.status(200).body(supplierInventories);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPPLIER')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody SupplierInventoryCreateDTO supplierInventory) {
        return ResponseEntity.status(201).body(supplierInventoryService.add(supplierInventory));
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPPLIER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody SupplierInventoryUpdateDTO supplierInventory, @PathVariable UUID id) {
        return ResponseEntity.status(201).body(supplierInventoryService.edit(id , supplierInventory));
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPPLIER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        supplierInventoryService.delete(id);
        return ResponseEntity.status(200).body("supplierInventory Was deleted");
    }

}
