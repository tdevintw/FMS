package dev.yassiraitelghari.fms.controller.food;

import dev.yassiraitelghari.fms.dto.request.category.CategoryCreateDTO;
import dev.yassiraitelghari.fms.dto.request.category.CategoryUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.category.CategoryDTO;
import dev.yassiraitelghari.fms.dto.response.category.CategoryDetailDTO;
import dev.yassiraitelghari.fms.service.food.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;


    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<CategoryDetailDTO> categories = categoryService.getAll();
        return ResponseEntity.status(200).body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        CategoryDetailDTO category = categoryService.findById(id);
        return ResponseEntity.status(200).body(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    private ResponseEntity<?> add(@RequestBody CategoryCreateDTO category) {
        CategoryDTO newCategory = categoryService.add(category);
        return ResponseEntity.status(201).body(newCategory);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.status(200).body("Category is deleted");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable UUID id, CategoryUpdateDTO category) {
       CategoryDetailDTO updatedCategory =  categoryService.edit(id , category);
       return  ResponseEntity.status(200).body(updatedCategory);
    }


}
