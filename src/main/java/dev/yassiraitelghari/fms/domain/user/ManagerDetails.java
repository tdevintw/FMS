package dev.yassiraitelghari.fms.domain.user;

import dev.yassiraitelghari.fms.domain.FavoriteSupplier;
import dev.yassiraitelghari.fms.domain.building.Building;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDetails extends User{
    private List<FavoriteSupplier> favoriteSuppliers;
    private List<Building> buildings ;
}
