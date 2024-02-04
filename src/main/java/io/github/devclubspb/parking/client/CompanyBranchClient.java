package io.github.devclubspb.parking.client;

import io.github.devclubspb.parking.payload.CompanyBranchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(value = "company-branch-service", path = "/api/branches")
public interface CompanyBranchClient {

    @GetMapping
    List<CompanyBranchResponse> getBranches();

    @GetMapping("/{branchId}")
    Optional<CompanyBranchResponse> getBranch(@PathVariable Long branchId);

}
