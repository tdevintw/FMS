package dev.yassiraitelghari.fms.mapper;

import dev.yassiraitelghari.fms.domain.location.City;
import dev.yassiraitelghari.fms.domain.location.Country;
import dev.yassiraitelghari.fms.dto.request.city.CityCreateDTO;
import dev.yassiraitelghari.fms.dto.request.city.CityUpdateDTO;
import dev.yassiraitelghari.fms.dto.request.country.CountryCreateDTO;
import dev.yassiraitelghari.fms.dto.request.country.CountryUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.city.CityDTO;
import dev.yassiraitelghari.fms.dto.response.city.CityDetailDTO;
import dev.yassiraitelghari.fms.dto.response.country.CountryDTO;
import dev.yassiraitelghari.fms.dto.response.country.CountryDetailDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    Country countryCreateDTOToCountry(CountryCreateDTO country);
    CountryDTO countryToCountryDTO(Country Country);
    CountryDetailDTO countryToCountryDetailDTO(Country country);
    Country countryUpdateDTOToCountry(CountryUpdateDTO country);
}
