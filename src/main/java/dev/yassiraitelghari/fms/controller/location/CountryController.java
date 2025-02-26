package dev.yassiraitelghari.fms.controller.location;

import dev.yassiraitelghari.fms.dto.request.country.CountryCreateDTO;
import dev.yassiraitelghari.fms.dto.request.country.CountryUpdateDTO;
import dev.yassiraitelghari.fms.dto.response.country.CountryDTO;
import dev.yassiraitelghari.fms.dto.response.country.CountryDetailDTO;
import dev.yassiraitelghari.fms.service.location.CountryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService CountryService) {
        this.countryService = CountryService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        CountryDTO country = countryService.findById(id);
        return ResponseEntity.status(200).body(country);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<CountryDetailDTO> countries = countryService.getAll();
        return ResponseEntity.status(200).body(countries);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','SUPPLIER','SHIPPER')")
    @PostMapping
    public ResponseEntity<?> add(@Valid @RequestBody CountryCreateDTO Country) {
        return ResponseEntity.status(201).body(countryService.add(Country));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','SUPPLIER','SHIPPER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CountryUpdateDTO country, @PathVariable UUID id) {
        return ResponseEntity.status(201).body(countryService.edit(id, country));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','SUPPLIER','SHIPPER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        countryService.delete(id);
        return ResponseEntity.status(200).body("Country Was deleted");
    }



}
