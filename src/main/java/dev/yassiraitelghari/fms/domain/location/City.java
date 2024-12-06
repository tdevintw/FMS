package dev.yassiraitelghari.fms.domain.location;

import dev.yassiraitelghari.fms.domain.supply.SupplierInventory;
import dev.yassiraitelghari.fms.domain.user.SupplierDetails;
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
public class City {
    private  UUID id ;
    private  String city;
    private Country country;
    private List<AvailableLocation> availableLocations ;
}
