package dev.yassiraitelghari.fms.mapper;


import dev.yassiraitelghari.fms.domain.building.Building;
import dev.yassiraitelghari.fms.domain.building.BuildingInventory;
import dev.yassiraitelghari.fms.dto.request.building.BuildingCreateDTO;
import dev.yassiraitelghari.fms.dto.request.building.BuildingUpdateDTO;
import dev.yassiraitelghari.fms.dto.request.buildingInventory.BuildingInventoryCreateDTO;
import dev.yassiraitelghari.fms.dto.request.buildingInventory.BuildingInventoryUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDetailDTO;
import dev.yassiraitelghari.fms.dto.response.buildingInventory.BuildingInventoryDTO;
import dev.yassiraitelghari.fms.dto.response.buildingInventory.BuildingInventoryDetailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BuildingInventoryMapper {
    BuildingInventory buildingInventoryCreateDTOToBuildingInventory(BuildingInventoryCreateDTO BuildingInventory);
    BuildingInventoryDTO buildingInventoryToBuildingInventoryDTO(BuildingInventory BuildingInventory);
    BuildingInventoryDetailDTO buildingInventoryToBuildingInventoryDetailDTO(BuildingInventory BuildingInventory);
    BuildingInventory buildingInventoryUpdateDTOToBuildingInventory(BuildingInventoryUpdateDTO BuildingInventory);
}
