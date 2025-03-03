package dev.yassiraitelghari.fms.dto.response.user;

import dev.yassiraitelghari.fms.dto.response.building.BuildingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ManagerDTO extends UserDTO{
   private List<BuildingDTO> buildings ;
}
