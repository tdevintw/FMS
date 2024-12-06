package dev.yassiraitelghari.fms.domain.food;

import dev.yassiraitelghari.fms.domain.building.BuildingInventory;
import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    private UUID id ;
    private String food ;
    private LocalDateTime creationDate ;
    private LocalDateTime updateTime ;
    private Category category ;
    private List<SupplierInventory> supplierInventories;
    private List<BuildingInventory> buildingInventories ;
}
