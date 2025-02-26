package dev.yassiraitelghari.fms.controller.location;

import dev.yassiraitelghari.fms.dto.request.city.CityCreateDTO;
import dev.yassiraitelghari.fms.dto.request.city.CityUpdateDTO;
import dev.yassiraitelghari.fms.dto.request.food.FoodCreateDTO;
import dev.yassiraitelghari.fms.dto.request.food.FoodUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.city.CityDTO;
import dev.yassiraitelghari.fms.dto.response.city.CityDetailDTO;
import dev.yassiraitelghari.fms.dto.response.food.FoodDTO;
import dev.yassiraitelghari.fms.service.food.FoodService;
import dev.yassiraitelghari.fms.service.location.CityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        CityDTO city = cityService.findById(id);
        return ResponseEntity.status(200).body(city);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<CityDetailDTO> cities = cityService.getAll();
        return ResponseEntity.status(200).body(cities);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody CityCreateDTO city) {
        return ResponseEntity.status(201).body(cityService.add(city));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CityUpdateDTO city, @PathVariable UUID id) {
        return ResponseEntity.status(201).body(cityService.edit(id , city));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        cityService.delete(id);
        return ResponseEntity.status(200).body("city Was deleted");
    }


}
