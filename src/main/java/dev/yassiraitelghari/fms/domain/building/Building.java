package dev.yassiraitelghari.fms.domain.building;

import dev.yassiraitelghari.fms.domain.enums.BuildingType;
import dev.yassiraitelghari.fms.domain.supply.Order;
import dev.yassiraitelghari.fms.domain.user.ManagerDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Building {
    private UUID id ;
    private String name ;
    private String location;
    private LocalDateTime creationDate ;
    private LocalDateTime updateTime ;
    private BuildingType buildingType;
    private ManagerDetails manager ;
    private List<BuildingInventory> buildingInventories;
    private List<Order> orders;
}
