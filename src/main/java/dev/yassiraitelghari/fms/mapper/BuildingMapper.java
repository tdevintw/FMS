package dev.yassiraitelghari.fms.mapper;

import dev.yassiraitelghari.fms.domain.building.Building;
import dev.yassiraitelghari.fms.dto.request.building.BuildingCreateDTO;
import dev.yassiraitelghari.fms.dto.request.building.BuildingUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BuildingMapper {
    Building buildingCreateDTOToBuilding(BuildingCreateDTO building);
    BuildingDTO buildingToBuildingDTO(Building building);
    BuildingDetailDTO buildingToBuildingDetailDTO(Building building);
    @Mapping(target = "id", ignore = true) // Ignore ID to prevent overwriting it
    Building buildingUpdateDTOToBuilding(BuildingUpdateDTO dto, @MappingTarget Building building);
}
