package io.github.devclubspb.parking.service.support;

import io.github.devclubspb.parking.client.EmployeeClient;
import io.github.devclubspb.parking.domain.Car;
import io.github.devclubspb.parking.domain.Driver;
import io.github.devclubspb.parking.payload.EmployeeResponse;
import io.github.devclubspb.parking.service.DriverEnrichmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "application.enrichment-mode", havingValue = "single")
public class SingleDriverEnrichmentService implements DriverEnrichmentService {

    protected final EmployeeClient employeeClient;

    @Override
    public void enrichDriver(Car car) {
        employeeClient.getEmployee(car.getDriver().getId())
                .map(this::mapResponse2Domain)
                .ifPresent(car::setDriver);
    }

    protected Driver mapResponse2Domain(EmployeeResponse response) {
        return Driver.builder()
                .id(response.getId())
                .name(response.getName())
                .build();
    }

}
