package dev.yassiraitelghari.fms.domain.food;

import dev.yassiraitelghari.fms.domain.building.BuildingInventory;
import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "foods")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    private String food ;
    private LocalDateTime creationDate ;
    private LocalDateTime updateTime ;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category ;
    @OneToMany(mappedBy = "food")
    private List<SupplierInventory> supplierInventories;
    @OneToMany(mappedBy = "food")
    private List<BuildingInventory> buildingInventories ;
}
