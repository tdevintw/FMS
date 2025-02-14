package dev.yassiraitelghari.fms.domain.building;

import dev.yassiraitelghari.fms.domain.food.Food;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "buildings_inventories")
public class BuildingInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    private double totalQuantity;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food ;
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
}
