package dev.yassiraitelghari.fms.mapper;

import dev.yassiraitelghari.fms.domain.building.Building;
import dev.yassiraitelghari.fms.dto.request.building.BuildingCreateDTO;
import dev.yassiraitelghari.fms.dto.request.building.BuildingUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDetailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuildingMapper {
    Building buildingCreateDTOToBuilding(BuildingCreateDTO building);
    BuildingDTO buildingToBuildingDTO(Building building);
    BuildingDetailDTO buildingToBuildingDetailDTO(Building building);
    Building buildingUpdateDTOToBuilding(BuildingUpdateDTO building);
}
