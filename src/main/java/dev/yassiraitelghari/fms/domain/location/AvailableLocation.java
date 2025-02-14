package dev.yassiraitelghari.fms.domain.location;

import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "available_locations")
public class AvailableLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @ManyToOne
    @JoinColumn(name = "supplier_inventory_id")
    private SupplierInventory supplierInventory;
}
