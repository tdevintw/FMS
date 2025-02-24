package dev.yassiraitelghari.fms.service.location;

import dev.yassiraitelghari.fms.domain.location.Country;
import dev.yassiraitelghari.fms.dto.request.country.CountryCreateDTO;
import dev.yassiraitelghari.fms.dto.request.country.CountryUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.country.CountryDTO;
import dev.yassiraitelghari.fms.dto.response.country.CountryDetailDTO;
import dev.yassiraitelghari.fms.exception.CountryUUIDNotFound;
import dev.yassiraitelghari.fms.mapper.CountryMapper;
import dev.yassiraitelghari.fms.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;


    public CountryService(CountryRepository CountryRepository, CountryMapper CountryMapper) {
        this.countryRepository = CountryRepository;
        this.countryMapper = CountryMapper;
    }


    public List<CountryDetailDTO> getAll() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream().map(countryMapper::countryToCountryDetailDTO).collect(Collectors.toList());
    }

    public CountryDetailDTO findById(UUID id) {
        Country Country = this.getById(id);
        return countryMapper.countryToCountryDetailDTO(Country);
    }

    public Country getById(UUID id) {
        return countryRepository.findById(id).orElseThrow(() -> new CountryUUIDNotFound("Country UUID not found"));
    }

    public CountryDTO add(CountryCreateDTO country) {
        Country newCountry = new Country();
        newCountry.setCountry(country.getCountry());
        Country savedCountry = countryRepository.save(newCountry);
        return countryMapper.countryToCountryDTO(savedCountry);
    }

    public CountryDetailDTO edit(UUID id, CountryUpdateDTO country) {
        Country updatedCountry = this.getById(id);
        updatedCountry.setCountry(country.getCountry());
        countryRepository.save(updatedCountry);
        return countryMapper.countryToCountryDetailDTO(updatedCountry);
    }


    public Country edit(Country country) {
        return countryRepository.save(country);
    }

    public void delete(UUID id) {
        Country country = this.getById(id);
        countryRepository.deleteById(country.getId());
    }
}
