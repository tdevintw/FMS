package dev.yassiraitelghari.fms.dto.request.city;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityCreateDTO {

    @NotBlank(message = "City is required.")
    @Size(min = 3, message = "City must be at least 3 characters long.")
    protected String city;

    @NotNull(message = "Country ID is required.")
    protected UUID countryId;
}
