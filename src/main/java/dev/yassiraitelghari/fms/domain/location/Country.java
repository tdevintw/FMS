package dev.yassiraitelghari.fms.domain.location;

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
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String country;
    private String imageUrl;
    @OneToMany(mappedBy = "country")
    List<City> cities;
}
