package dev.yassiraitelghari.fms.domain.location;

import dev.yassiraitelghari.fms.domain.building.Building;
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
    private  UUID id ;
    private  String city;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
    @OneToMany(mappedBy = "city" )
    private List<AvailableLocation> availableLocations ;
    @OneToMany(mappedBy = "city")
    private List<Building> buildings ;
}
