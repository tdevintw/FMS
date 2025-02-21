package dev.yassiraitelghari.fms.mapper;

import dev.yassiraitelghari.fms.domain.food.Category;
import dev.yassiraitelghari.fms.dto.request.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category categoryDTOToCategory(CategoryDTO categoryDTO);
}
