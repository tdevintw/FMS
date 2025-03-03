package dev.yassiraitelghari.fms.dto.response.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShipperDTO extends UserDTO{
    private List<ShipperDTO> shipments;
}
