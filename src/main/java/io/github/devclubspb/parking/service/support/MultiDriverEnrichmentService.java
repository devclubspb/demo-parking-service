package io.github.devclubspb.parking.service.support;

import io.github.devclubspb.parking.client.EmployeeClient;
import io.github.devclubspb.parking.domain.Car;
import io.github.devclubspb.parking.domain.Driver;
import io.github.devclubspb.parking.service.DriverEnrichmentService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "application.enrichment-mode", havingValue = "multi")
public class MultiDriverEnrichmentService
        extends SingleDriverEnrichmentService
        implements DriverEnrichmentService {

    public MultiDriverEnrichmentService(EmployeeClient employeeClient) {
        super(employeeClient);
    }

    @Override
    public void enrichDrivers(Collection<Car> cars) {
        Set<Long> ids = cars.stream().map(Car::getDriver).map(Driver::getId).collect(Collectors.toSet());
        Map<Long, Driver> driverById = employeeClient.getEmployees(ids).stream()
                .map(this::mapResponse2Domain)
                .collect(Collectors.toMap(Driver::getId, Function.identity()));
        for (Car car : cars) {
            Driver driver = driverById.get(car.getDriver().getId());
            if (driver != null) {
                car.setDriver(driver);
            }
        }
    }

}
