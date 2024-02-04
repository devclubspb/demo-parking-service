package io.github.devclubspb.parking.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarRequest {

    private Long ownerId;
    private Long driverId;

    private String brand;
    private String model;
    private String classType;

}
