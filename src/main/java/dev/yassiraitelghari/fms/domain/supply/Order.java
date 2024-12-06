package dev.yassiraitelghari.fms.domain.supply;

import dev.yassiraitelghari.fms.domain.building.Building;
import dev.yassiraitelghari.fms.domain.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private UUID id ;
    private double quantity ;
    private LocalDateTime creationDate ;
    private LocalDateTime updateDate;
    private SupplierInventory supplierInventory ;
    private Building building;
    private Payment payment;
    private OrderStatus orderStatus;
    private Shipment shipment;
}
