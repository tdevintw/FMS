package dev.yassiraitelghari.fms.dto.request.building;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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

    @NotBlank(message = "Building name is required.")
    private String name;

    @NotBlank(message = "Location is required.")
    private UUID cityId;

    @Pattern(regexp = "HOTEL|MOTEL|RESTAURANT", message = "Role must be HOTEL, MOTEL or RESTAURANT")
    private String buildingType;

    @NotNull(message = "Manager ID is required.")
    private UUID managerId;


}
