package dev.yassiraitelghari.fms.dto.request.building;

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
    protected String name;
    protected String location;
    protected String buildingType;
    protected UUID managerId;
}
