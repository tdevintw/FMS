package dev.yassiraitelghari.fms.dto.response.country;

import dev.yassiraitelghari.fms.dto.response.city.CityDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CountryDetailDTO extends CountryDTO {
    private List<CityDTO> cities;
}
