package dev.yassiraitelghari.fms.domain.building;

import dev.yassiraitelghari.fms.domain.enums.BuildingType;
import dev.yassiraitelghari.fms.domain.location.City;
import dev.yassiraitelghari.fms.domain.supply.Order;
import dev.yassiraitelghari.fms.domain.user.Manager;
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
@Table(name = "buildings")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private UUID id ;
    private String name ;
    private LocalDateTime creationDate ;
    private LocalDateTime updateDate ;
    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;
    @ManyToOne
    @JoinColumn(name = "city_id" , nullable = false)
    private City city ;
    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager ;
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<BuildingInventory> buildingInventories;
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private List<Order> orders;
}
