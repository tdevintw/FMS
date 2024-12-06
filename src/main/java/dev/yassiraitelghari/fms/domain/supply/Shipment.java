package dev.yassiraitelghari.fms.domain.supply;

import dev.yassiraitelghari.fms.domain.user.ShipperDetails;
import dev.yassiraitelghari.fms.domain.user.SupplierDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Shipment {
    private UUID id ;
    private String currentLocation ;
    private LocalDateTime creationDate ;
    private LocalDateTime updateDate ;
    private ShipperDetails shipper;
    private Order order ;
}
