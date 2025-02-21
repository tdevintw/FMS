package dev.yassiraitelghari.fms.dto.response.food;

import dev.yassiraitelghari.fms.dto.response.category.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDTO {
    private UUID id;
    private String food;
    private CategoryDTO category;
}
