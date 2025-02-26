package dev.yassiraitelghari.fms.service.location;

import dev.yassiraitelghari.fms.domain.food.Category;
import dev.yassiraitelghari.fms.domain.location.City;
import dev.yassiraitelghari.fms.domain.location.Country;
import dev.yassiraitelghari.fms.dto.request.category.CategoryCreateDTO;
import dev.yassiraitelghari.fms.dto.request.category.CategoryUpdateDTO;
import dev.yassiraitelghari.fms.dto.request.city.CityCreateDTO;
import dev.yassiraitelghari.fms.dto.request.city.CityUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.category.CategoryDTO;
import dev.yassiraitelghari.fms.dto.response.category.CategoryDetailDTO;
import dev.yassiraitelghari.fms.dto.response.city.CityDTO;
import dev.yassiraitelghari.fms.dto.response.city.CityDetailDTO;
import dev.yassiraitelghari.fms.dto.response.country.CountryDTO;
import dev.yassiraitelghari.fms.exception.CategoryUUIDNotFound;
import dev.yassiraitelghari.fms.exception.CityUUIDNotFound;
import dev.yassiraitelghari.fms.mapper.CityMapper;
import dev.yassiraitelghari.fms.mapper.CountryMapper;
import dev.yassiraitelghari.fms.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final CountryService countryService;
    private final CountryMapper countryMapper;

    public CityService(CityRepository cityRepository, CityMapper cityMapper, CountryService countryService, CountryMapper countryMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
        this.countryService = countryService;
        this.countryMapper = countryMapper;
    }


    public List<CityDetailDTO> getAll() {
        List<City> cities = cityRepository.findAll();
        List<CityDetailDTO> citiesDTO = new ArrayList<>();
        cities.forEach(city -> {
            CountryDTO country = countryMapper.countryToCountryDTO(city.getCountry());
            CityDetailDTO cityDTO = cityMapper.cityToCityDetailDTO(city);
            cityDTO.setCountryDTO(country);
            citiesDTO.add(cityDTO);
        });
        return citiesDTO;
    }

    public CityDetailDTO findById(UUID id) {
        City city = this.getById(id);
        CountryDTO country = countryMapper.countryToCountryDTO(city.getCountry());
        CityDetailDTO cityDTO = cityMapper.cityToCityDetailDTO(city);
        cityDTO.setCountryDTO(country);
        return cityDTO;
    }

    public City getById(UUID id) {
        return cityRepository.findById(id).orElseThrow(() -> new CityUUIDNotFound("City UUID not found"));
    }

    public CityDTO add(CityCreateDTO city) {
        Country country = countryService.getById(city.getCountryId());
        City newCity = new City();
        newCity.setCity(city.getCity());
        newCity.setCountry(country);
        City savedCity = cityRepository.save(newCity);
        return cityMapper.cityToCityDTO(savedCity);
    }

    public CityDetailDTO edit(UUID id, CityUpdateDTO city) {
        Country country = countryService.getById(city.getCountryId());
        City updatedCity = this.getById(id);
        CountryDTO countryDTO = countryMapper.countryToCountryDTO(country);
        updatedCity.setCity(city.getCity());
        updatedCity.setCountry(country);
        cityRepository.save(updatedCity);
        CityDetailDTO cityDetailDTO = cityMapper.cityToCityDetailDTO(updatedCity);
        cityDetailDTO.setCountryDTO(countryDTO);
        return cityDetailDTO;
    }


    public City edit(City city) {
        return cityRepository.save(city);
    }

    public void delete(UUID id) {
        City city = this.getById(id);
        cityRepository.deleteById(city.getId());
    }
}
