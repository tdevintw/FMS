package dev.yassiraitelghari.fms.domain.food;

import jakarta.persistence.*;
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
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    private String category ;
    private LocalDateTime creationDate ;
    private LocalDateTime updateDate ;
    @OneToMany(mappedBy = "category")
    private List<Food> foods;

}
