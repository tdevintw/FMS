package dev.yassiraitelghari.fms.dto.response.building;

import dev.yassiraitelghari.fms.dto.response.city.CityDTO;
import dev.yassiraitelghari.fms.dto.response.city.CityDetailDTO;
import dev.yassiraitelghari.fms.dto.response.user.UserDTO;
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
public class BuildingDTO {
    protected UUID id;
    protected String name;
    protected CityDetailDTO city;
    protected String buildingType;
    protected String address;
    protected UserDTO manager;
}
