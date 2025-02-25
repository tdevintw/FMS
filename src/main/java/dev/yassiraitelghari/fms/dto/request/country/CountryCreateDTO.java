package dev.yassiraitelghari.fms.dto.request.country;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CountryCreateDTO {

    @NotBlank(message = "Country is required.")
    @Size(min = 3, message = "Country must be at least 3 characters long.")
    protected String country;
}
