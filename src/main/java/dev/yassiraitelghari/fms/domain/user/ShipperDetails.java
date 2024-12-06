package dev.yassiraitelghari.fms.domain.user;

import dev.yassiraitelghari.fms.domain.supply.Shipment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipperDetails  extends User{
   private List<Shipment> shipments;
}
