package dev.yassiraitelghari.fms.domain.user;

import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
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
public class Supplier extends User {
    private boolean isSupplierRegistered;
    @OneToMany(mappedBy = "supplier")
    private List<SupplierInventory> inventories;
}
