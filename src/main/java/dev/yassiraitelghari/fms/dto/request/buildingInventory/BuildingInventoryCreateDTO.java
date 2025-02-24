package dev.yassiraitelghari.fms.dto.request.buildingInventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
public class BuildingInventoryCreateDTO {
    protected double totalQuantity ;
    protected UUID foodId ;
    protected UUID buildingId;
}
