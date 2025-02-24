package dev.yassiraitelghari.fms.service.building;

import dev.yassiraitelghari.fms.domain.building.Building;
import dev.yassiraitelghari.fms.domain.food.Category;
import dev.yassiraitelghari.fms.dto.request.building.BuildingCreateDTO;
import dev.yassiraitelghari.fms.dto.request.building.BuildingUpdateDTO;
import dev.yassiraitelghari.fms.dto.request.category.CategoryCreateDTO;
import dev.yassiraitelghari.fms.dto.request.category.CategoryUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDetailDTO;
import dev.yassiraitelghari.fms.dto.response.category.CategoryDTO;
import dev.yassiraitelghari.fms.dto.response.category.CategoryDetailDTO;
import dev.yassiraitelghari.fms.exception.BuildingUUIDNotFound;
import dev.yassiraitelghari.fms.exception.CategoryUUIDNotFound;
import dev.yassiraitelghari.fms.mapper.BuildingMapper;
import dev.yassiraitelghari.fms.mapper.CategoryMapper;
import dev.yassiraitelghari.fms.repository.BuildingRepository;
import dev.yassiraitelghari.fms.repository.CategoryRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BuildingService {
    private final BuildingRepository buildingRepository;
    private final BuildingMapper buildingMapper;

    public BuildingService(BuildingRepository buildingRepository, BuildingMapper buildingMapper) {
        this.buildingRepository = buildingRepository;
        this.buildingMapper = buildingMapper;
    }


    public List<BuildingDetailDTO> getAll() {
        List<Building> buildings = buildingRepository.findAll();
        return buildings.stream().map(buildingMapper::buildingToBuildingDetailDTO).collect(Collectors.toList());
    }

    public BuildingDetailDTO findById(UUID id) {
        Building building = this.getById(id);
        return buildingMapper.buildingToBuildingDetailDTO(building);
    }

    public Building getById(UUID id) {
        return buildingRepository.findById(id).orElseThrow(() -> new BuildingUUIDNotFound("Building UUID not found"));
    }

    public BuildingDTO add(BuildingCreateDTO building) {
        Building newBuilding = buildingMapper.buildingCreateDTOToBuilding(building);
        return buildingMapper.buildingToBuildingDTO(buildingRepository.save(newBuilding));
    }

    public BuildingDetailDTO edit(UUID id, BuildingUpdateDTO building) {
        Building updatedBuilding = this.getById(id);
        return buildingMapper.buildingToBuildingDetailDTO(edit(buildingMapper.buildingUpdateDTOToBuilding(building)));
    }


    public Building edit(Building building) {
        return buildingRepository.save(building);
    }

    public void delete(UUID id) {
        Building building = this.getById(id);
        buildingRepository.deleteById(building.getId());
    }
}
