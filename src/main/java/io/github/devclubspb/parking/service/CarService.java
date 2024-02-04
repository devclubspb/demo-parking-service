package io.github.devclubspb.parking.service;

import io.github.devclubspb.parking.domain.Car;
import io.github.devclubspb.parking.domain.NewCar;

import java.util.List;

public interface CarService {

    List<Car> getAllCars();

    Car createCar(NewCar newCar);

}
