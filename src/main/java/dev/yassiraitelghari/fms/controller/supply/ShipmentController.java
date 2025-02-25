package dev.yassiraitelghari.fms.controller.supply;


import dev.yassiraitelghari.fms.dto.request.shipment.ShipmentCreateDTO;
import dev.yassiraitelghari.fms.dto.request.shipment.ShipmentUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.shipment.ShipmentDTO;
import dev.yassiraitelghari.fms.dto.response.shipment.ShipmentDetailDTO;
import dev.yassiraitelghari.fms.service.supply.ShipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {
    private final ShipmentService shipmentService;


    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        ShipmentDetailDTO shipment = shipmentService.findById(id);
        return ResponseEntity.status(200).body(shipment);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ShipmentDetailDTO> shipments = shipmentService.getAll();
        return ResponseEntity.status(200).body(shipments);
    }

    @PreAuthorize("hasAnyRole('ADMIN','SHIPPER')")
    @PostMapping
    public ResponseEntity<?> add(@RequestBody ShipmentCreateDTO shipment) {
        return ResponseEntity.status(201).body(shipmentService.add(shipment));
    }

    @PreAuthorize("hasAnyRole('ADMIN','SHIPPER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody ShipmentUpdateDTO shipment, @PathVariable UUID id) {
        return ResponseEntity.status(201).body(shipmentService.edit(id, shipment));
    }

    @PreAuthorize("hasAnyRole('ADMIN','SHIPPER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        shipmentService.delete(id);
        return ResponseEntity.status(200).body("shipment Was deleted");
    }

}
