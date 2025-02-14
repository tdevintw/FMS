package dev.yassiraitelghari.fms.domain.user;

import dev.yassiraitelghari.fms.domain.FavoriteSupplier;
import dev.yassiraitelghari.fms.domain.building.Building;
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
@Table(name = "managers")
public class ManagerDetails extends User{
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<FavoriteSupplier> favoriteSuppliers;
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Building> buildings ;
}
