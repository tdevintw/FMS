package dev.yassiraitelghari.fms.dto.request.supplierInventory;

import dev.yassiraitelghari.fms.domain.food.Food;
import dev.yassiraitelghari.fms.domain.location.AvailableLocation;
import dev.yassiraitelghari.fms.domain.supply.Order;
import dev.yassiraitelghari.fms.domain.user.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierInventoryCreateDTO {

    protected double price;
    protected UUID supplierId;
    protected UUID foodId;
}

