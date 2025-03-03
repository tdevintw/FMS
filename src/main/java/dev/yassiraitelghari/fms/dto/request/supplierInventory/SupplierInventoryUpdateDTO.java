package dev.yassiraitelghari.fms.dto.request.supplierInventory;

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
public class SupplierInventoryUpdateDTO{
    @Min(value = 0, message = "Price must be greater than or equal to 0.")
    protected double price;

    @NotNull(message = "Food ID is required.")
    protected UUID foodId;

}
