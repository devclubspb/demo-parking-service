package io.github.devclubspb.parking.service;

import io.github.devclubspb.parking.domain.Car;

import java.util.Collection;

public interface OwnerEnrichmentService {

    void enrichOwner(Car car);

    default void enrichOwners(Collection<Car> cars) {
        cars.forEach(this::enrichOwner);
    }

}
