package io.github.devclubspb.parking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car")
public class CarEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long ownerId;
    private Long driverId;

    private String brand;
    private String model;
    private String classType;

}
