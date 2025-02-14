package dev.yassiraitelghari.fms.domain.user;

import dev.yassiraitelghari.fms.domain.FavoriteSupplier;
import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suppliers")
public class SupplierDetails extends User {
    private boolean isSupplierRegistered;
    private String location;
    private double rate;
    @OneToMany(mappedBy = "supplier")
    private List<FavoriteSupplier> favoriteSuppliers;
    @OneToMany(mappedBy = "supplier")
    private List<SupplierInventory> inventories;
}
