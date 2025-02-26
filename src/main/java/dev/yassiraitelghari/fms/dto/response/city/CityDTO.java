package dev.yassiraitelghari.fms.dto.response.city;

import dev.yassiraitelghari.fms.dto.response.country.CountryDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityDTO {
    protected UUID id;
    protected String city;
    protected CountryDTO countryDTO;
}
