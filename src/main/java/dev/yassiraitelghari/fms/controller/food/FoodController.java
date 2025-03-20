package dev.yassiraitelghari.fms.controller.food;

import dev.yassiraitelghari.fms.dto.request.food.FoodCreateDTO;
import dev.yassiraitelghari.fms.dto.request.food.FoodUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.food.FoodDTO;
import dev.yassiraitelghari.fms.service.food.FoodService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/foods")
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        FoodDTO food = foodService.findById(id);
        return ResponseEntity.status(200).body(food);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<FoodDTO> foods = foodService.getAll();
        return ResponseEntity.status(200).body(foods);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestPart("food") FoodCreateDTO food , @RequestPart("image")MultipartFile file) {
        FoodDTO addedFood = foodService.add(food, file);
        return ResponseEntity.status(201).body(addedFood);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid  @RequestPart("food") FoodUpdateDTO food, @PathVariable UUID id  , @RequestPart(value = "image" , required = false) MultipartFile file) {
        return ResponseEntity.status(201).body(foodService.edit(food, id , file));
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        foodService.delete(id);
        return ResponseEntity.status(200).body("Food Was deleted");
    }

}