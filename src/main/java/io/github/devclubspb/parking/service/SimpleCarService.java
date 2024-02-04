package io.github.devclubspb.parking.service;

import io.github.devclubspb.parking.domain.Car;
import io.github.devclubspb.parking.domain.CarClass;
import io.github.devclubspb.parking.domain.NewCar;
import io.github.devclubspb.parking.entity.CarEntity;
import io.github.devclubspb.parking.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleCarService implements CarService {

    private final CarRepository carRepository;

    @Override
    public Car createCar(NewCar newCar) {
        CarEntity entity = CarEntity.builder()
                .ownerId(newCar.getOwnerId())
                .driverId(newCar.getDriverId())
                .brand(newCar.getBrand())
                .model(newCar.getModel())
                .classType(newCar.getClassType().name())
                .build();
        CarEntity savedEntity = carRepository.save(entity);
        return mapEntity2Domain(savedEntity);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAllSortedByModel().stream()
                .map(this::mapEntity2Domain)
                .toList();
    }

    private Car mapEntity2Domain(CarEntity entity) {
        return Car.builder()
                .id(entity.getId())
                .ownerId(entity.getOwnerId())
                .driverId(entity.getDriverId())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .classType(CarClass.valueOf(entity.getClassType()))
                .build();
    }

}
