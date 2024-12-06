package dev.yassiraitelghari.fms.domain;

import dev.yassiraitelghari.fms.domain.user.ManagerDetails;
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
public class FavoriteSupplier {
    private UUID id ;
    private ManagerDetails manager;
    private SupplierDetails supplier ;
    private LocalDateTime addedAt ;
    private double ordersCount ;
}
