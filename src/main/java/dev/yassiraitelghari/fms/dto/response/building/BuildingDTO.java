package dev.yassiraitelghari.fms.dto.response.building;

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
    protected UUID id ;
    protected String name;
    protected String location;
    protected String buildingType;
    protected UUID managerId;
}
