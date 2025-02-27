package dev.yassiraitelghari.fms.dto.response.building;

import dev.yassiraitelghari.fms.dto.response.city.CityDTO;
import dev.yassiraitelghari.fms.dto.response.user.UserDTO;
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
    protected CityDTO city;
    protected String buildingType;
    protected UserDTO manager;
}
