package dev.yassiraitelghari.fms.dto.request.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateDTO {

    @NotBlank(message = "Category is required.")
    @Size(min = 3, message = "Category must be at least 3 characters long.")
    protected String category;
}
