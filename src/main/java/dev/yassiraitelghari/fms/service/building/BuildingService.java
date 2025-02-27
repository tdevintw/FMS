package dev.yassiraitelghari.fms.service.building;

import dev.yassiraitelghari.fms.domain.building.Building;
import dev.yassiraitelghari.fms.domain.enums.Role;
import dev.yassiraitelghari.fms.domain.food.Food;
import dev.yassiraitelghari.fms.domain.location.City;
import dev.yassiraitelghari.fms.domain.user.Manager;
import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.dto.request.building.BuildingCreateDTO;
import dev.yassiraitelghari.fms.dto.request.building.BuildingUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDetailDTO;
import dev.yassiraitelghari.fms.exception.BuildingUUIDNotFound;
import dev.yassiraitelghari.fms.exception.BuildingAccessDeniedException;
import dev.yassiraitelghari.fms.exception.NotAuthorizedToAssignBuildingToManagerException;
import dev.yassiraitelghari.fms.mapper.BuildingMapper;
import dev.yassiraitelghari.fms.repository.BuildingRepository;
import dev.yassiraitelghari.fms.service.food.FoodService;
import dev.yassiraitelghari.fms.service.location.CityService;
import dev.yassiraitelghari.fms.service.user.ManagerService;
import dev.yassiraitelghari.fms.service.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BuildingService {
    private final BuildingRepository buildingRepository;
    private final BuildingMapper buildingMapper;
    private final UserService userService;
    private final ManagerService managerService;
    private final CityService cityService;

    public BuildingService(BuildingRepository buildingRepository, BuildingMapper buildingMapper, UserService userService, ManagerService managerService, FoodService foodService, CityService cityService) {
        this.buildingRepository = buildingRepository;
        this.buildingMapper = buildingMapper;
        this.userService = userService;
        this.managerService = managerService;
        this.cityService = cityService;
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
        Manager user = managerService.findById(building.getManagerId());
        isAuthorizedToAssignManageBuilding(user);
        City city = cityService.getById(building.getCityId());
        Building newBuilding = buildingMapper.buildingCreateDTOToBuilding(building);
        newBuilding.setManager(user);
        newBuilding.setCity(city);
        return buildingMapper.buildingToBuildingDTO(buildingRepository.save(newBuilding));
    }

    public BuildingDetailDTO edit(UUID id, BuildingUpdateDTO building) {
        Building updatedBuilding = this.getById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        if (user.getRole().equals(Role.MANAGER) && !user.getId().equals(updatedBuilding.getManager().getId())) {
            throw new BuildingAccessDeniedException("Access Denied To Edit This Building");
        }

        return buildingMapper.buildingToBuildingDetailDTO(edit(buildingMapper.buildingUpdateDTOToBuilding(building)));
    }


    public Building edit(Building building) {
        return buildingRepository.save(building);
    }

    public void delete(UUID id) {
        Building building = this.getById(id);
        Manager user = managerService.findById(building.getManager().getId());
        isAuthorizedToAssignManageBuilding(user);
        buildingRepository.deleteById(building.getId());
    }

    public void isAuthorizedToAssignManageBuilding(Manager user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User authUser = userService.getByUsername(username);
        if (!authUser.getRole().equals(Role.ADMIN) && !authUser.getId().equals(user.getId())) {
            throw new NotAuthorizedToAssignBuildingToManagerException("You manage this building");
        }
    }
}
