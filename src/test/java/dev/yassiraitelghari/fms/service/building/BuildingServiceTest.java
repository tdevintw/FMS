package dev.yassiraitelghari.fms.service.building;

import dev.yassiraitelghari.fms.domain.building.Building;
import dev.yassiraitelghari.fms.domain.enums.Role;
import dev.yassiraitelghari.fms.domain.location.City;
import dev.yassiraitelghari.fms.domain.user.Manager;
import dev.yassiraitelghari.fms.domain.user.User;
import dev.yassiraitelghari.fms.dto.request.building.BuildingCreateDTO;
import dev.yassiraitelghari.fms.dto.request.building.BuildingUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDetailDTO;
import dev.yassiraitelghari.fms.exception.BuildingUUIDNotFound;
import dev.yassiraitelghari.fms.exception.NotAuthorizedToAssignBuildingToManagerException;
import dev.yassiraitelghari.fms.mapper.BuildingMapper;
import dev.yassiraitelghari.fms.repository.BuildingRepository;
import dev.yassiraitelghari.fms.service.location.CityService;
import dev.yassiraitelghari.fms.service.user.ManagerService;
import dev.yassiraitelghari.fms.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class BuildingServiceTest {

    @InjectMocks
    private BuildingService buildingService;

    @Mock
    private BuildingRepository buildingRepository;

    @Mock
    private BuildingMapper buildingMapper;

    @Mock
    private UserService userService;

    @Mock
    private ManagerService managerService;

    @Mock
    private CityService cityService;

    @Mock
    private Authentication authentication;

    private final UUID buildingId = UUID.randomUUID();
    private final UUID managerId = UUID.randomUUID();
    private final UUID cityId = UUID.randomUUID();
    private final String username = "admin";

    @BeforeEach
    void setup() {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getName()).thenReturn(username);
    }

    @Test
    void getAll_ShouldReturnAllBuildings() {
        List<Building> buildings = List.of(new Building());
        when(buildingRepository.findAll()).thenReturn(buildings);
        when(buildingMapper.buildingToBuildingDetailDTO(any())).thenReturn(new BuildingDetailDTO());

        List<BuildingDetailDTO> result = buildingService.getAll();

        assertThat(result).hasSize(1);
    }

    @Test
    void findById_ShouldReturnBuildingDetailDTO() {
        Building building = new Building();
        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));
        when(buildingMapper.buildingToBuildingDetailDTO(building)).thenReturn(new BuildingDetailDTO());

        BuildingDetailDTO result = buildingService.findById(buildingId);

        assertThat(result).isNotNull();
    }

    @Test
    void findById_ShouldThrow_WhenBuildingNotFound() {
        when(buildingRepository.findById(buildingId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> buildingService.findById(buildingId))
                .isInstanceOf(BuildingUUIDNotFound.class);
    }

    @Test
    void add_ShouldCreateBuilding_WhenAuthorized() {
        BuildingCreateDTO dto = new BuildingCreateDTO();
        dto.setManagerId(managerId);
        dto.setCityId(cityId);

        Manager manager = new Manager();
        manager.setId(managerId);
        User adminUser = new User();
        adminUser.setUsername(username);
        adminUser.setRole(Role.ADMIN);

        when(managerService.findById(managerId)).thenReturn(manager);
        when(userService.getByUsername(username)).thenReturn(adminUser);
        when(cityService.getById(cityId)).thenReturn(new City());
        when(buildingMapper.buildingCreateDTOToBuilding(dto)).thenReturn(new Building());
        when(buildingMapper.buildingToBuildingDTO(any())).thenReturn(new BuildingDTO());

        BuildingDTO result = buildingService.add(dto);

        assertThat(result).isNotNull();
    }

    @Test
    void add_ShouldThrow_WhenUnauthorized() {
        BuildingCreateDTO dto = new BuildingCreateDTO();
        dto.setManagerId(managerId);

        Manager manager = new Manager();
        manager.setId(managerId);
        User user = new User();
        user.setUsername(username);
        user.setRole(Role.MANAGER);
        user.setId(UUID.randomUUID());

        when(managerService.findById(managerId)).thenReturn(manager);
        when(userService.getByUsername(username)).thenReturn(user);

        assertThatThrownBy(() -> buildingService.add(dto))
                .isInstanceOf(NotAuthorizedToAssignBuildingToManagerException.class);
    }

    @Test
    void edit_ShouldUpdateBuilding_WhenAuthorized() {
        BuildingUpdateDTO dto = new BuildingUpdateDTO();
        dto.setCityId(cityId);

        Building existingBuilding = new Building();
        existingBuilding.setId(buildingId);
        Manager manager = new Manager();
        manager.setId(managerId);
        existingBuilding.setManager(manager);

        User adminUser = new User();
        adminUser.setUsername(username);
        adminUser.setRole(Role.ADMIN);

        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(existingBuilding));
        when(managerService.findById(managerId)).thenReturn(manager);
        when(userService.getByUsername(username)).thenReturn(adminUser);
        when(buildingMapper.buildingUpdateDTOToBuilding(eq(dto), any())).thenReturn(existingBuilding);
        when(cityService.getById(cityId)).thenReturn(new City());
        when(buildingRepository.save(existingBuilding)).thenReturn(existingBuilding);
        when(buildingMapper.buildingToBuildingDetailDTO(existingBuilding)).thenReturn(new BuildingDetailDTO());

        BuildingDetailDTO result = buildingService.edit(buildingId, dto);

        assertThat(result).isNotNull();
    }

    @Test
    void delete_ShouldRemoveBuilding_WhenAuthorized() {
        Building building = new Building();
        building.setId(buildingId);
        Manager manager = new Manager();
        manager.setId(managerId);
        building.setManager(manager);

        User adminUser = new User();
        adminUser.setUsername(username);
        adminUser.setRole(Role.ADMIN);

        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));
        when(managerService.findById(managerId)).thenReturn(manager);
        when(userService.getByUsername(username)).thenReturn(adminUser);

        buildingService.delete(buildingId);

        verify(buildingRepository).deleteById(buildingId);
    }

    @Test
    void delete_ShouldThrow_WhenUnauthorized() {
        Building building = new Building();
        building.setId(buildingId);
        Manager manager = new Manager();
        manager.setId(managerId);
        building.setManager(manager);

        User user = new User();
        user.setUsername(username);
        user.setRole(Role.MANAGER);
        user.setId(UUID.randomUUID());

        when(buildingRepository.findById(buildingId)).thenReturn(Optional.of(building));
        when(managerService.findById(managerId)).thenReturn(manager);
        when(userService.getByUsername(username)).thenReturn(user);

        assertThatThrownBy(() -> buildingService.delete(buildingId))
                .isInstanceOf(NotAuthorizedToAssignBuildingToManagerException.class);
    }
}
