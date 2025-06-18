package ait.car.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@ToString
public class CarDtoLombok {
    private String manufacturer;
    private int year;
    private Set<String> models;

}
