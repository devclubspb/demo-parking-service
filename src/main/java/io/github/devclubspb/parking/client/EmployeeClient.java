package io.github.devclubspb.parking.client;

import io.github.devclubspb.parking.payload.EmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@FeignClient(value = "employee-service", path = "/api/employees")
public interface EmployeeClient {

    @GetMapping
    default List<EmployeeResponse> getEmployees() {
        return getEmployees(Set.of());
    }

    @GetMapping
    List<EmployeeResponse> getEmployees(@RequestParam(required = false) Set<Long> ids);

    @GetMapping("/{employeeId}")
    Optional<EmployeeResponse> getEmployee(@PathVariable Long employeeId);

}
