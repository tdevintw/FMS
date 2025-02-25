package dev.yassiraitelghari.fms.dto.request.buildingInventory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BuildingInventoryCreateDTO {

    @Min(value = 1, message = "Total quantity must be at least 1.")
    @NotNull(message = "Quantity is required.")

    protected double totalQuantity;

    @NotNull(message = "Food ID is required.")
    protected UUID foodId;

    @NotNull(message = "Building ID is required.")
    protected UUID buildingId;
}
