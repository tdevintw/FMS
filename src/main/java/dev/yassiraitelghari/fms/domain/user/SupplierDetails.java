package dev.yassiraitelghari.fms.domain.user;

import dev.yassiraitelghari.fms.domain.FavoriteSupplier;
import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDetails extends User {
    private boolean isSupplierRegistered;
    private String location;
    private double rate;
    private List<FavoriteSupplier> favoriteSuppliers;
    private List<SupplierInventory> inventories;
}
