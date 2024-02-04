package io.github.devclubspb.parking.service.support;

import io.github.devclubspb.parking.domain.*;
import io.github.devclubspb.parking.entity.CarEntity;
import io.github.devclubspb.parking.repository.CarRepository;
import io.github.devclubspb.parking.service.CarService;
import io.github.devclubspb.parking.service.DriverEnrichmentService;
import io.github.devclubspb.parking.service.OwnerEnrichmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleCarService implements CarService {

    private final CarRepository carRepository;

    private final OwnerEnrichmentService ownerEnrichmentService;
    private final DriverEnrichmentService driverEnrichmentService;

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
        List<Car> cars = carRepository.findAllSortedByModel().stream()
                .map(this::mapEntity2Domain)
                .toList();
        ownerEnrichmentService.enrichOwners(cars);
        driverEnrichmentService.enrichDrivers(cars);
        return cars;
    }

    private Car mapEntity2Domain(CarEntity entity) {
        return Car.builder()
                .id(entity.getId())
                .owner(Owner.builder()
                        .id(entity.getOwnerId())
                        .name("Unknown")
                        .build())
                .driver(Driver.builder()
                        .id(entity.getDriverId())
                        .name("Unknown")
                        .build())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .classType(CarClass.valueOf(entity.getClassType()))
                .build();
    }

}
