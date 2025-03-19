package dev.yassiraitelghari.fms.service.food;

import dev.yassiraitelghari.fms.domain.food.Category;
import dev.yassiraitelghari.fms.dto.request.category.CategoryCreateDTO;
import dev.yassiraitelghari.fms.dto.request.category.CategoryUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.category.CategoryDTO;
import dev.yassiraitelghari.fms.dto.response.category.CategoryDetailDTO;
import dev.yassiraitelghari.fms.exception.CategoryUUIDNotFound;
import dev.yassiraitelghari.fms.mapper.CategoryMapper;
import dev.yassiraitelghari.fms.repository.CategoryRepository;
import dev.yassiraitelghari.fms.util.SaveImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::categoryToCategoryDTO).collect(Collectors.toList());
    }

    public CategoryDetailDTO findById(UUID id) {
        Category category = this.getById(id);
        return categoryMapper.categoryToCategoryDetailDTO(category);
    }

    public Category getById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryUUIDNotFound("Category UUID not found"));
    }

    public CategoryDTO add(CategoryCreateDTO category, MultipartFile file) {
        try {
            Category newCategory = new Category();
            newCategory.setCategory(category.getCategory());
            if (!file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                String extension = "";
                int dotIndex = originalFilename.lastIndexOf(".");
                if (dotIndex > 0) {
                    extension = originalFilename.substring(dotIndex);
                    originalFilename = originalFilename.substring(0, dotIndex);
                }
                String newFileName = originalFilename + "_" + LocalDateTime.now()
                        .toString().replace(":", "-") + extension;
                String imagePath = SaveImage.save(file, newFileName);
                newCategory.setImageUrl(imagePath);
            }
            Category savedCategory = categoryRepository.save(newCategory);
            return categoryMapper.categoryToCategoryDTO(savedCategory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CategoryDetailDTO edit(UUID id, CategoryUpdateDTO category, MultipartFile file) {
        try {
            Category updatedCategory = this.getById(id);
            updatedCategory.setCategory(category.getCategory());
            if (!file.isEmpty()) {
                String originalFilename = file.getOriginalFilename();
                String extension = "";
                int dotIndex = originalFilename.lastIndexOf(".");
                if (dotIndex > 0) {
                    extension = originalFilename.substring(dotIndex);
                    originalFilename = originalFilename.substring(0, dotIndex);
                }
                String newFileName = originalFilename + "_" + LocalDateTime.now()
                        .toString().replace(":", "-") + extension;
                String imagePath = SaveImage.save(file, newFileName);
                updatedCategory.setImageUrl(imagePath);
            }
            categoryRepository.save(updatedCategory);
            return categoryMapper.categoryToCategoryDetailDTO(updatedCategory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public Category edit(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(UUID id) {
        Category category = this.getById(id);
        categoryRepository.deleteById(category.getId());
    }


}