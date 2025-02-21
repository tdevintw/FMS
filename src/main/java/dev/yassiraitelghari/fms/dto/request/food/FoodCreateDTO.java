package dev.yassiraitelghari.fms.dto.request.food;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FoodCreateDTO {
    protected String food;
    protected UUID categoryId;
}
