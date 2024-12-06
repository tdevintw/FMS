package dev.yassiraitelghari.fms.domain.food;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private UUID id ;
    private String category ;
    private LocalDateTime creationDate ;
    private LocalDateTime updateDate ;
    private List<Food> foods;
}
