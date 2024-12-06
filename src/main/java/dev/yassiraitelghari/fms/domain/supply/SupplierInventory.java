package dev.yassiraitelghari.fms.domain.supply;

import dev.yassiraitelghari.fms.domain.food.Food;
import dev.yassiraitelghari.fms.domain.location.AvailableLocation;
import dev.yassiraitelghari.fms.domain.location.City;
import dev.yassiraitelghari.fms.domain.user.SupplierDetails;
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
public class SupplierInventory {
    private UUID id ;
    private double price;
    private LocalDateTime creationDate ;
    private LocalDateTime updateTime ;
    private List<AvailableLocation> availableLocations;
    private SupplierDetails supplier ;
    private Food food ;
    private List<Order> orders;

}
