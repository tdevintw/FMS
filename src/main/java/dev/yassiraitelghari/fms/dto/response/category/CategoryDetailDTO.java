package dev.yassiraitelghari.fms.dto.response.category;

import dev.yassiraitelghari.fms.dto.response.food.FoodDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDetailDTO {
   private  UUID id;
   private String category;
   private List<FoodDTO> foods;
}
