package dev.yassiraitelghari.fms.domain;

import dev.yassiraitelghari.fms.domain.user.ManagerDetails;
import dev.yassiraitelghari.fms.domain.user.SupplierDetails;
import jakarta.persistence.*;
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
@Entity
@Table(name = "favorite_suppliers")
public class FavoriteSupplier {
    @Id
    private UUID id ;
    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private ManagerDetails manager;
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private SupplierDetails supplier ;
    private LocalDateTime addedAt ;
    private double ordersCount ;
}
