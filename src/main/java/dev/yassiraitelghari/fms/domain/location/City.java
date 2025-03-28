package dev.yassiraitelghari.fms.domain.location;

import dev.yassiraitelghari.fms.domain.building.Building;
import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String city;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @OneToMany(mappedBy = "city")
    private List<AvailableLocation> availableLocations;
    @OneToMany(mappedBy = "city")
    private List<Building> buildings;
    @OneToMany(mappedBy = "city")
    private List<SupplierInventory> supplierInventories;
}