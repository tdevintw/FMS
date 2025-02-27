package dev.yassiraitelghari.fms.dto.request.building;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class BuildingUpdateDTO {

    @Size(min = 3 , message = "Building Name should be at least 3 characters" )
    @NotBlank(message = "Building name is required.")
    private String name;
    @NotNull(message = "City is required")
    private UUID cityId;
    @Pattern(regexp = "HOTEL|MOTEL|RESTAURANT", message = "Role must be HOTEL, MOTEL or RESTAURANT")
    private String buildingType;

}
