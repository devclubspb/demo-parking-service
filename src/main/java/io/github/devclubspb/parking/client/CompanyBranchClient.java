package io.github.devclubspb.parking.client;

import io.github.devclubspb.parking.payload.CompanyBranchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@FeignClient(value = "company-branch-service", path = "/api/branches")
public interface CompanyBranchClient {

    default List<CompanyBranchResponse> getBranches() {
        return getBranches(Set.of());
    }

    @GetMapping
    List<CompanyBranchResponse> getBranches(@RequestParam(required = false) Set<Long> ids);

    @GetMapping("/{branchId}")
    Optional<CompanyBranchResponse> getBranch(@PathVariable Long branchId);

}
