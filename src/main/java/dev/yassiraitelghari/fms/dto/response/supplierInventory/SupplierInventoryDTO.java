package dev.yassiraitelghari.fms.dto.response.supplierInventory;

import dev.yassiraitelghari.fms.dto.response.city.CityDetailDTO;
import dev.yassiraitelghari.fms.dto.response.food.FoodDTO;
import dev.yassiraitelghari.fms.dto.response.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierInventoryDTO {
    protected UUID id ;
    protected double price ;
    protected UserDTO supplier;
    protected FoodDTO food;
    protected CityDetailDTO city;

}
