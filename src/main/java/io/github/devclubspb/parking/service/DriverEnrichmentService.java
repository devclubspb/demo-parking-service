package io.github.devclubspb.parking.service;

import io.github.devclubspb.parking.domain.Car;

import java.util.Collection;

public interface DriverEnrichmentService {

    void enrichDriver(Car car);

    default void enrichDrivers(Collection<Car> cars) {
        cars.forEach(this::enrichDriver);
    }

}
