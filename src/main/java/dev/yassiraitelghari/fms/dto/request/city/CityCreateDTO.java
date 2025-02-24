package dev.yassiraitelghari.fms.dto.request.city;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityCreateDTO {
    protected String city;
    protected UUID countryId;
}
