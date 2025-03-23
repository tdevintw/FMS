package dev.yassiraitelghari.fms.dto.request.building;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuildingCreateDTO {

    @Size(min = 3, message = "Building Name should be at least 3 characters")
    @NotBlank(message = "Building name is required.")
    private String name;
    @NotNull(message = "City is required")
    private UUID cityId;

    @Pattern(regexp = "HOTEL|MOTEL|RESTAURANT", message = "Role must be HOTEL, MOTEL or RESTAURANT")
    private String buildingType;
    @NotNull(message = "Address is required")
    private String address;

    @NotNull(message = "Manager ID is required.")
    private UUID managerId;


}
