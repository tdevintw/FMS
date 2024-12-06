package dev.yassiraitelghari.fms.domain.building;

import dev.yassiraitelghari.fms.domain.food.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuildingInventory {
    private UUID id ;
    private double totalQuantity;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Food food ;
}
