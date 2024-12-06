package dev.yassiraitelghari.fms.domain.location;

import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AvailableLocation {
    private UUID id ;
    private City city;
    private SupplierInventory supplierInventory;
}
