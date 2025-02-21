package dev.yassiraitelghari.fms.service.food;

import dev.yassiraitelghari.fms.domain.food.Category;
import dev.yassiraitelghari.fms.dto.request.CategoryDTO;
import dev.yassiraitelghari.fms.mapper.CategoryMapper;
import dev.yassiraitelghari.fms.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public Category create(CategoryDTO categoryDTO) {
        Category category = categoryMapper.categoryDTOToCategory(categoryDTO);
        category.setCreationDate(LocalDateTime.now());
        category.setUpdateDate(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    public boolean delete(UUID id) {
        if (this.categoryRepository.findById(id).isEmpty()) return false;
        else categoryRepository.deleteById(id);
        return true;
    }

    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
    }

//    public List<dev.yassiraitelghari.fms.dto.response.CategoryDTO> findByCategory(String category) {
//        return categoryRepository.findAllByCategoryContainingIgnoreCase(category);
//    }

    public Category edit(UUID id, CategoryDTO categoryDTO) {
        Optional<Category> category = this.categoryRepository.findById(id);
        if (category.isEmpty()) return null;
        else {
            category.get().setCategory(categoryDTO.getCategory());
            return categoryRepository.save(category.get());
        }
    }

    public List<dev.yassiraitelghari.fms.dto.response.CategoryDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::categoryToCategoryDTO).toList();
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

}
