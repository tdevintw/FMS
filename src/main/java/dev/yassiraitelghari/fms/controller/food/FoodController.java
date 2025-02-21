package dev.yassiraitelghari.fms.controller.food;

import dev.yassiraitelghari.fms.dto.request.food.FoodCreateDTO;
import dev.yassiraitelghari.fms.dto.request.food.FoodUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.food.FoodDTO;
import dev.yassiraitelghari.fms.service.food.FoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<?> add(FoodCreateDTO food) {
        return ResponseEntity.status(201).body(foodService.add(food));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody FoodUpdateDTO food, @PathVariable UUID id) {
        return ResponseEntity.status(201).body(foodService.edit(food, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        foodService.delete(id);
        return ResponseEntity.status(200).body("Food Was deleted");
    }

}
