package dev.yassiraitelghari.fms.service.building;


import dev.yassiraitelghari.fms.domain.building.Building;
import dev.yassiraitelghari.fms.domain.building.BuildingInventory;
import dev.yassiraitelghari.fms.domain.food.Food;
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
import dev.yassiraitelghari.fms.service.food.FoodService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BuildingInventoryService {


    private final BuildingInventoryRepository buildingInventoryRepository;
    private final BuildingInventoryMapper buildingInventoryMapper;
    private final BuildingService buildingService;
    private final FoodService foodService;

    public BuildingInventoryService(BuildingInventoryRepository buildingInventoryRepository, BuildingInventoryMapper buildingInventoryMapper, BuildingService buildingService, FoodService foodService) {
        this.buildingInventoryRepository = buildingInventoryRepository;
        this.buildingInventoryMapper = buildingInventoryMapper;
        this.buildingService = buildingService;
        this.foodService = foodService;
    }

    public List<BuildingInventoryDTO> getAll() {
        List<BuildingInventory> buildingInventories = buildingInventoryRepository.findAll();
        return buildingInventories.stream().map(buildingInventory -> buildingInventoryMapper.buildingInventoryToBuildingInventoryDTO(buildingInventory)).collect(Collectors.toList());
    }

    public BuildingInventoryDTO findById(UUID id) {
        BuildingInventory buildingInventory = this.getById(id);
        return buildingInventoryMapper.buildingInventoryToBuildingInventoryDTO(buildingInventory);
    }

    public BuildingInventory getById(UUID id) {
        return buildingInventoryRepository.findById(id).orElseThrow(() -> new BuildingInventoryUUIDNotFound("BuildingInventory UUID not found"));
    }

    public BuildingInventoryDTO add(BuildingInventoryCreateDTO buildingInventory) {
        Building building = buildingService.getById(buildingInventory.getBuildingId());
        Food food = foodService.getById(buildingInventory.getFoodId());
        BuildingInventory newBuildingInventory = buildingInventoryMapper.buildingInventoryCreateDTOToBuildingInventory(buildingInventory);
        newBuildingInventory.setBuilding(building);
        newBuildingInventory.setFood(food);
        return buildingInventoryMapper.buildingInventoryToBuildingInventoryDTO(buildingInventoryRepository.save(newBuildingInventory));
    }

    public BuildingInventoryDetailDTO edit(UUID id, BuildingInventoryUpdateDTO buildingInventory) {
        Building building = buildingService.getById(buildingInventory.getBuildingId());
        Food food = foodService.getById(buildingInventory.getFoodId());
        BuildingInventory updatedBuildingInventory = this.getById(id);
        updatedBuildingInventory.setBuilding(building);
        updatedBuildingInventory.setFood(food);
        updatedBuildingInventory.setTotalQuantity(buildingInventory.getTotalQuantity());
        edit(updatedBuildingInventory);
        return buildingInventoryMapper.buildingInventoryToBuildingInventoryDetailDTO(updatedBuildingInventory);
    }

    public BuildingInventory edit(BuildingInventory buildingInventory) {
        return buildingInventoryRepository.save(buildingInventory);
    }

    public void delete(UUID id) {
        BuildingInventory buildingInventory = this.getById(id);
        buildingInventoryRepository.deleteById(buildingInventory.getId());
    }
}