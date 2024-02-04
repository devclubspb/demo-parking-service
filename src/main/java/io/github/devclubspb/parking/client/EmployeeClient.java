package io.github.devclubspb.parking.client;

import io.github.devclubspb.parking.payload.EmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(value = "employee-service", path = "/api/employees")
public interface EmployeeClient {

    @GetMapping
    List<EmployeeResponse> getEmployees();

    @GetMapping("/{employeeId}")
    Optional<EmployeeResponse> getEmployee(@PathVariable Long employeeId);

}
