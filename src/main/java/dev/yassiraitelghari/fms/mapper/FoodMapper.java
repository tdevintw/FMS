package dev.yassiraitelghari.fms.mapper;

import dev.yassiraitelghari.fms.domain.food.Food;
import dev.yassiraitelghari.fms.dto.response.food.FoodDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    FoodDTO foodToFoodDTO(Food food);
}
