package io.github.devclubspb.parking.service;

import io.github.devclubspb.parking.client.CompanyBranchClient;
import io.github.devclubspb.parking.client.EmployeeClient;
import io.github.devclubspb.parking.domain.*;
import io.github.devclubspb.parking.entity.CarEntity;
import io.github.devclubspb.parking.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleCarService implements CarService {

    private final CarRepository carRepository;

    private final CompanyBranchClient branchClient;
    private final EmployeeClient employeeClient;

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
        Owner owner = branchClient.getBranch(entity.getOwnerId())
                .map(branch -> Owner.builder()
                        .id(branch.getId())
                        .name(branch.getName())
                        .address(branch.getAddress())
                        .build())
                .orElseGet(() -> Owner.builder()
                        .id(entity.getOwnerId())
                        .name("Unknown")
                        .build());
        Driver driver = employeeClient.getEmployee(entity.getDriverId())
                .map(branch -> Driver.builder()
                        .id(branch.getId())
                        .name(branch.getName())
                        .build())
                .orElseGet(() -> Driver.builder()
                        .id(entity.getDriverId())
                        .name("Unknown")
                        .build());
        return Car.builder()
                .id(entity.getId())
                .owner(owner)
                .driver(driver)
                .brand(entity.getBrand())
                .model(entity.getModel())
                .classType(CarClass.valueOf(entity.getClassType()))
                .build();
    }

}
