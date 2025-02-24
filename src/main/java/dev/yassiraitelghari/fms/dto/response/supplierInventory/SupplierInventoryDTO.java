package dev.yassiraitelghari.fms.dto.response.supplierInventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierInventoryDTO {
    protected UUID id ;
    protected double price ;
    protected UUID supplierId;
    protected UUID foodId;
}
