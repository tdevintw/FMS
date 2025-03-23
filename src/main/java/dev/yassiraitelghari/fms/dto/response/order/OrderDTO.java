package dev.yassiraitelghari.fms.dto.response.order;

import dev.yassiraitelghari.fms.domain.enums.OrderStatus;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDTO;
import dev.yassiraitelghari.fms.dto.response.supplierInventory.SupplierInventoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    protected UUID id;
    protected double quantity;
    protected double totalPrice;
    protected SupplierInventoryDTO supplierInventory;
    protected BuildingDTO building;
    protected OrderStatus orderStatus;

}
