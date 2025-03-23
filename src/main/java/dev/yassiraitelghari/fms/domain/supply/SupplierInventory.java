package dev.yassiraitelghari.fms.domain.supply;

import dev.yassiraitelghari.fms.domain.food.Food;
import dev.yassiraitelghari.fms.domain.location.AvailableLocation;
import dev.yassiraitelghari.fms.domain.location.City;
import dev.yassiraitelghari.fms.domain.user.Supplier;
import jakarta.persistence.JoinColumn;
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
@Table(name = "suppliers_inventory")
public class SupplierInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id ;
    private double price;
    private LocalDateTime creationDate ;
    private LocalDateTime updateDate ;
    @OneToMany(mappedBy = "supplierInventory", cascade = CascadeType.ALL)
    private List<AvailableLocation> availableLocations;
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier ;
    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food ;
    @OneToMany(mappedBy = "supplierInventory" , cascade = CascadeType.ALL)
    private List<Order> orders;
    @ManyToOne
    @JoinColumn(name = "city_id" , nullable = false)
    private City city ;


}
