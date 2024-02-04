package io.github.devclubspb.parking.controller;

import io.github.devclubspb.parking.domain.Car;
import io.github.devclubspb.parking.domain.CarClass;
import io.github.devclubspb.parking.domain.NewCar;
import io.github.devclubspb.parking.payload.CarRequest;
import io.github.devclubspb.parking.payload.CarResponse;
import io.github.devclubspb.parking.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    @PostMapping
    public CarResponse createCar(@RequestBody CarRequest request) {
        NewCar newCar = NewCar.builder()
                .ownerId(request.getOwnerId())
                .driverId(request.getDriverId())
                .brand(request.getBrand())
                .model(request.getModel())
                .classType(CarClass.valueOf(request.getClassType()))
                .build();
        Car createdCar = carService.createCar(newCar);
        return mapDomain2Response(createdCar);
    }

    @GetMapping
    public List<CarResponse> getCars() {
        return carService.getAllCars().stream()
                .map(this::mapDomain2Response)
                .toList();
    }

    private CarResponse mapDomain2Response(Car domain) {
        return CarResponse.builder()
                .id(domain.getId())
                .owner(domain.getOwner().getName())
                .address(domain.getOwner().getAddress())
                .driver(domain.getDriver().getName())
                .brand(domain.getBrand())
                .model(domain.getModel())
                .classType(domain.getClassType().name())
                .build();
    }

}
