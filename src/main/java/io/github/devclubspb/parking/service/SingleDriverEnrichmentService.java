package io.github.devclubspb.parking.service;

import io.github.devclubspb.parking.client.EmployeeClient;
import io.github.devclubspb.parking.domain.Car;
import io.github.devclubspb.parking.domain.Driver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SingleDriverEnrichmentService implements DriverEnrichmentService {

    private final EmployeeClient employeeClient;

    @Override
    public void enrichDriver(Car car) {
        employeeClient.getEmployee(car.getDriver().getId())
                .map(branch -> Driver.builder()
                        .id(branch.getId())
                        .name(branch.getName())
                        .build())
                .ifPresent(car::setDriver);
    }

}
