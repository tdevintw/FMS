package dev.yassiraitelghari.fms.dto.request.building;

import jakarta.validation.constraints.NotBlank;
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
public class BuildingCreateDTO {

    @NotBlank(message = "Building name is required.")
    private String name;

    @NotBlank(message = "Location is required.")
    private String location;

    @NotBlank(message = "Building type is required.")
    private String buildingType;

    @NotNull(message = "Manager ID is required.")
    private UUID managerId;
}
