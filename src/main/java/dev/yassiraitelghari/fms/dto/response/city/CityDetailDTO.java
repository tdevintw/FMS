package dev.yassiraitelghari.fms.dto.response.city;

import dev.yassiraitelghari.fms.dto.response.country.CountryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CityDetailDTO extends CityDTO{
    private CountryDTO country;
}
