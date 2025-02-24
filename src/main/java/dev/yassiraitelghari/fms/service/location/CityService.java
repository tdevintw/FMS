package dev.yassiraitelghari.fms.service.location;

import dev.yassiraitelghari.fms.domain.food.Category;
import dev.yassiraitelghari.fms.domain.location.City;
import dev.yassiraitelghari.fms.dto.request.category.CategoryCreateDTO;
import dev.yassiraitelghari.fms.dto.request.category.CategoryUpdateDTO;
import dev.yassiraitelghari.fms.dto.request.city.CityCreateDTO;
import dev.yassiraitelghari.fms.dto.request.city.CityUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.category.CategoryDTO;
import dev.yassiraitelghari.fms.dto.response.category.CategoryDetailDTO;
import dev.yassiraitelghari.fms.dto.response.city.CityDTO;
import dev.yassiraitelghari.fms.dto.response.city.CityDetailDTO;
import dev.yassiraitelghari.fms.exception.CategoryUUIDNotFound;
import dev.yassiraitelghari.fms.exception.CityUUIDNotFound;
import dev.yassiraitelghari.fms.mapper.CityMapper;
import dev.yassiraitelghari.fms.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;


    public CityService(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }


    public List<CityDetailDTO> getAll(){
        List<City> cities = cityRepository.findAll();
        return cities.stream().map(cityMapper::cityToCityDetailDTO).collect(Collectors.toList());
    }

    public CityDetailDTO findById(UUID id) {
        City city = this.getById(id);
        return cityMapper.cityToCityDetailDTO(city);
    }

    public City getById(UUID id) {
        return cityRepository.findById(id).orElseThrow(() -> new CityUUIDNotFound("City UUID not found"));
    }

    public CityDTO add(CityCreateDTO city) {
        City newCity = new City();
        newCity.setCity(city.getCity());
        City savedCity = cityRepository.save(newCity);
        return cityMapper.cityToCityDTO(savedCity);
    }

    public CityDetailDTO edit(UUID id, CityUpdateDTO city) {
        City updatedCity = this.getById(id);
        updatedCity.setCity(city.getCity());
        cityRepository.save(updatedCity);
        return cityMapper.cityToCityDetailDTO(updatedCity);
    }


    public City edit(City city) {
        return cityRepository.save(city);
    }

    public void delete(UUID id) {
        City city = this.getById(id);
        cityRepository.deleteById(city.getId());
    }
}
