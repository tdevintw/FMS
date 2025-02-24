package dev.yassiraitelghari.fms.service.building;


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
import dev.yassiraitelghari.fms.exception.BuildingInventoryUUIDNotFound;
import dev.yassiraitelghari.fms.exception.BuildingUUIDNotFound;
import dev.yassiraitelghari.fms.mapper.BuildingInventoryMapper;
import dev.yassiraitelghari.fms.mapper.BuildingMapper;
import dev.yassiraitelghari.fms.repository.BuildingInventoryRepository;
import dev.yassiraitelghari.fms.repository.BuildingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BuildingInventoryService {


    private final BuildingInventoryRepository buildingInventoryRepository;
    private final BuildingInventoryMapper buildingInventoryMapper;

    public BuildingInventoryService(BuildingInventoryRepository buildingInventoryRepository, BuildingInventoryMapper buildingInventoryMapper) {
        this.buildingInventoryRepository = buildingInventoryRepository;
        this.buildingInventoryMapper = buildingInventoryMapper;
    }


    public List<BuildingInventoryDetailDTO> getAll() {
        List<BuildingInventory> buildingInventories = buildingInventoryRepository.findAll();
        return buildingInventories.stream().map(buildingInventoryMapper::buildingInventoryToBuildingInventoryDetailDTO).collect(Collectors.toList());
    }

    public BuildingInventoryDetailDTO findById(UUID id) {
        BuildingInventory buildingInventory = this.getById(id);
        return buildingInventoryMapper.buildingInventoryToBuildingInventoryDetailDTO(buildingInventory);
    }

    public BuildingInventory getById(UUID id) {
        return buildingInventoryRepository.findById(id).orElseThrow(() -> new BuildingInventoryUUIDNotFound("BuildingInventory UUID not found"));
    }

    public BuildingInventoryDTO add(BuildingInventoryCreateDTO building) {
        BuildingInventory newBuilding = buildingInventoryMapper.buildingInventoryCreateDTOToBuildingInventory(building);
        return buildingInventoryMapper.buildingInventoryToBuildingInventoryDTO(buildingInventoryRepository.save(newBuilding));
    }

    public BuildingInventoryDetailDTO edit(UUID id, BuildingInventoryUpdateDTO buildingInventory) {
        BuildingInventory updatedBuildingInventory = this.getById(id);
        return buildingInventoryMapper.buildingInventoryToBuildingInventoryDetailDTO(edit(buildingInventoryMapper.buildingInventoryUpdateDTOToBuildingInventory(buildingInventory)));
    }


    public BuildingInventory edit(BuildingInventory buildingInventory) {
        return buildingInventoryRepository.save(buildingInventory);
    }

    public void delete(UUID id) {
        BuildingInventory buildingInventory = this.getById(id);
        buildingInventoryRepository.deleteById(buildingInventory.getId());
    }


}



