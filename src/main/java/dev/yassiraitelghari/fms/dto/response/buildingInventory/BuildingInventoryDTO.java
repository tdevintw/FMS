package dev.yassiraitelghari.fms.dto.response.buildingInventory;


import dev.yassiraitelghari.fms.dto.response.building.BuildingDTO;
import dev.yassiraitelghari.fms.dto.response.food.FoodDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuildingInventoryDTO {
    protected UUID id ;
    private double totalQuantity;
    protected FoodDTO food ;
    protected BuildingDTO building;
}

