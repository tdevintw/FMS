package dev.yassiraitelghari.fms.service.food;

import dev.yassiraitelghari.fms.domain.food.Category;
import dev.yassiraitelghari.fms.domain.food.Food;
import dev.yassiraitelghari.fms.dto.request.food.FoodCreateDTO;
import dev.yassiraitelghari.fms.dto.request.food.FoodUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.food.FoodDTO;
import dev.yassiraitelghari.fms.exception.CategoryUUIDNotFound;
import dev.yassiraitelghari.fms.exception.FoodUUIDNotFound;
import dev.yassiraitelghari.fms.mapper.FoodMapper;
import dev.yassiraitelghari.fms.repository.CategoryRepository;
import dev.yassiraitelghari.fms.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FoodService {
    private final FoodRepository foodRepository;
    private final CategoryService categoryService;
    private final FoodMapper foodMapper;

    public FoodService(FoodRepository foodRepository, CategoryService categoryService, CategoryRepository categoryRepository, FoodMapper foodMapper) {
        this.foodRepository = foodRepository;
        this.categoryService = categoryService;
        this.foodMapper = foodMapper;
    }

    public FoodDTO findById(UUID id) {
        Food food = foodRepository.findById(id).orElseThrow(() -> new FoodUUIDNotFound("Food UUID not found"));
        return foodMapper.foodToFoodDTO(food);
    }

    public FoodDTO add(FoodCreateDTO food) {
        UUID id = food.getCategoryId();
        Category category = categoryService.getById(id);
        Food newFood = new Food();
        newFood.setFood(food.getFood());
        newFood.setCategory(category);
        category.setFood(newFood);
        categoryService.edit(category);
        return foodMapper.foodToFoodDTO(newFood);
    }

    public FoodDTO edit(FoodUpdateDTO food, UUID id) {
        Category category = categoryService.getById(id);
        Food updatedFood = foodRepository.findById(id).orElseThrow(() -> new FoodUUIDNotFound("Food UUID not found"));
        updatedFood.setFood(food.getFood());
        updatedFood.setCategory(category);
        foodRepository.save(updatedFood);
        return foodMapper.foodToFoodDTO(updatedFood);
    }

    public void delete(UUID id) {
        Food food = foodRepository.findById(id).orElseThrow(() -> new FoodUUIDNotFound("Food UUID not found"));
        foodRepository.deleteById(id);
    }

    public List<FoodDTO> getAll(){
        List<Food> foods = foodRepository.findAll();
        return foods.stream().map(foodMapper::foodToFoodDTO).collect(Collectors.toList());
    }
}
