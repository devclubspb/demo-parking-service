package io.github.devclubspb.parking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewCar {

    private Long ownerId;
    private Long driverId;

    private String brand;
    private String model;
    private CarClass classType;

}
