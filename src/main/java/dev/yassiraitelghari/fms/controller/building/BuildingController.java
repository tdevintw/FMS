package dev.yassiraitelghari.fms.controller.building;


import dev.yassiraitelghari.fms.dto.request.building.BuildingCreateDTO;
import dev.yassiraitelghari.fms.dto.request.building.BuildingUpdateDTO;
import dev.yassiraitelghari.fms.dto.request.category.CategoryCreateDTO;
import dev.yassiraitelghari.fms.dto.request.category.CategoryUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDTO;
import dev.yassiraitelghari.fms.dto.response.building.BuildingDetailDTO;
import dev.yassiraitelghari.fms.dto.response.category.CategoryDTO;
import dev.yassiraitelghari.fms.dto.response.category.CategoryDetailDTO;
import dev.yassiraitelghari.fms.service.building.BuildingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/buildings")
public class BuildingController {
    private BuildingService buildingService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<BuildingDetailDTO> buildings = buildingService.getAll();
        return ResponseEntity.status(200).body(buildings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        BuildingDetailDTO building = buildingService.findById(id);
        return ResponseEntity.status(200).body(building);
    }

    @PostMapping
    private ResponseEntity<?> add(@RequestBody BuildingCreateDTO building) {
        BuildingDTO newBuilding = buildingService.add(building);
        return ResponseEntity.status(201).body(newBuilding);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        buildingService.delete(id);
        return ResponseEntity.status(200).body("Building is deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable UUID id, BuildingUpdateDTO building) {
        BuildingDetailDTO updatedBuilding =  buildingService.edit(id , building);
        return  ResponseEntity.status(200).body(updatedBuilding);
    }

}
