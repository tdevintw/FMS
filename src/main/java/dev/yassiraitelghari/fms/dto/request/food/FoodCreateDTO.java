package dev.yassiraitelghari.fms.dto.request.food;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Food name is required.")
    @Size(min = 3, message = "Food name must be at least 3 characters long.")
    protected String food;

    @NotNull(message = "Category ID is required.")
    protected UUID categoryId;
}
