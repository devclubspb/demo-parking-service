package io.github.devclubspb.parking.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {

    private Long id;

    private String owner;
    private String address;
    private String driver;

    private String brand;
    private String model;
    private String classType;

}
