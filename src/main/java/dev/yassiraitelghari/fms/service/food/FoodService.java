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
import dev.yassiraitelghari.fms.util.SaveImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        Food food = getById(id);
        return foodMapper.foodToFoodDTO(food);
    }

    public Food getById(UUID id) {
        return foodRepository.findById(id).orElseThrow(() -> new FoodUUIDNotFound("Food UUID not found"));

    }

    public FoodDTO add(FoodCreateDTO food , MultipartFile file) {

        try{
            UUID id = food.getCategoryId();
            Category category = categoryService.getById(id);
            Food newFood = new Food();
            newFood.setFood(food.getFood());
            newFood.setCategory(category);
            category.setFood(newFood);
            if(!file.isEmpty()){
                String imagePath = SaveImage.save(file);
                newFood.setImageUrl(imagePath);
            }
            categoryService.edit(category);
            return foodMapper.foodToFoodDTO(foodRepository.save(newFood));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public FoodDTO edit(FoodUpdateDTO food, UUID id , MultipartFile file) {
        try{
            Category category = categoryService.getById(food.getCategoryId());
            Food updatedFood = foodRepository.findById(id).orElseThrow(() -> new FoodUUIDNotFound("Food UUID not found"));
            updatedFood.setFood(food.getFood());
            updatedFood.setCategory(category);
            if(!file.isEmpty()){
                String imagePath = SaveImage.save(file);
                updatedFood.setImageUrl(imagePath);
            }
            updatedFood = foodRepository.save(updatedFood);
            return foodMapper.foodToFoodDTO(updatedFood);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(UUID id) {
        foodRepository.findById(id).orElseThrow(() -> new FoodUUIDNotFound("Food UUID not found"));
        foodRepository.deleteById(id);
    }

    public List<FoodDTO> getAll() {
        List<Food> foods = foodRepository.findAll();
        return foods.stream().map(foodMapper::foodToFoodDTO).collect(Collectors.toList());
    }
}