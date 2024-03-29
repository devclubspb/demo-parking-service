package io.github.devclubspb.parking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private Long id;

    private Owner owner;
    private Driver driver;

    private String brand;
    private String model;
    private CarClass classType;

}
