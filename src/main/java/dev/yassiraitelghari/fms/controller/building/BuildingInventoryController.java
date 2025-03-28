package dev.yassiraitelghari.fms.controller.building;

import dev.yassiraitelghari.fms.dto.request.building.BuildingCreateDTO;
import dev.yassiraitelghari.fms.dto.request.building.BuildingUpdateDTO;
import dev.yassiraitelghari.fms.dto.request.buildingInventory.BuildingInventoryCreateDTO;
import dev.yassiraitelghari.fms.dto.request.buildingInventory.BuildingInventoryUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDetailDTO;
import dev.yassiraitelghari.fms.dto.response.buildingInventory.BuildingInventoryDTO;
import dev.yassiraitelghari.fms.dto.response.buildingInventory.BuildingInventoryDetailDTO;
import dev.yassiraitelghari.fms.service.building.BuildingInventoryService;
import dev.yassiraitelghari.fms.service.building.BuildingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/buildingInventories")
public class BuildingInventoryController {
    private final BuildingInventoryService buildingInventoryService;

    public BuildingInventoryController(BuildingInventoryService buildingInventoryService) {
        this.buildingInventoryService = buildingInventoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<BuildingInventoryDTO> buildings = buildingInventoryService.getAll();
        return ResponseEntity.status(200).body(buildings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        BuildingInventoryDTO buildingInventory = buildingInventoryService.findById(id);
        return ResponseEntity.status(200).body(buildingInventory);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping
    public ResponseEntity<?> add(@Valid  @RequestBody BuildingInventoryCreateDTO buildingInventory) {
        BuildingInventoryDTO newBuildingInventory = buildingInventoryService.add(buildingInventory);
        return ResponseEntity.status(201).body(newBuildingInventory);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        buildingInventoryService.delete(id);
        return ResponseEntity.status(200).body("BuildingInventory is deleted");
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable UUID id,@Valid @RequestBody BuildingInventoryUpdateDTO buildingInventory) {
        BuildingInventoryDTO updatedBuildingInventory =  buildingInventoryService.edit(id , buildingInventory);
        return  ResponseEntity.status(200).body(updatedBuildingInventory);
    }
}
