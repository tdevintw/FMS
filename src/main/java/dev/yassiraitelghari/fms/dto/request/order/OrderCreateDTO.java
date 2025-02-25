package dev.yassiraitelghari.fms.dto.request.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateDTO {

    @Min(value = 1, message = "Quantity must be at least 1.")
    protected double quantity;

    @NotNull(message = "Supplier Inventory ID is required.")
    private UUID supplierInventoryId;

    @NotNull(message = "Building ID is required.")
    private UUID buildingId;
}
