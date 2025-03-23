package dev.yassiraitelghari.fms.domain.supply;

import dev.yassiraitelghari.fms.domain.building.Building;
import dev.yassiraitelghari.fms.domain.enums.OrderStatus;
import jakarta.persistence.*;
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
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    private double quantity ;
    private double totalPrice ;
    private LocalDateTime creationDate ;
    private LocalDateTime updateDate ;
    @ManyToOne
    @JoinColumn(name = "supplier_inventory_id")
    private SupplierInventory supplierInventory ;
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
    @Enumerated
    private OrderStatus orderStatus;
    @OneToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;
}
