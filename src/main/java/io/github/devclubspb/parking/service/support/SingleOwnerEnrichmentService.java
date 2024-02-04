package io.github.devclubspb.parking.service.support;

import io.github.devclubspb.parking.client.CompanyBranchClient;
import io.github.devclubspb.parking.domain.Car;
import io.github.devclubspb.parking.domain.Owner;
import io.github.devclubspb.parking.payload.CompanyBranchResponse;
import io.github.devclubspb.parking.service.OwnerEnrichmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "application.enrichment-mode", havingValue = "single")
public class SingleOwnerEnrichmentService implements OwnerEnrichmentService {

    protected final CompanyBranchClient branchClient;

    @Override
    public void enrichOwner(Car car) {
        Long ownerId = car.getOwner().getId();
        branchClient.getBranch(ownerId)
                .map(this::mapResponse2Domain)
                .ifPresent(car::setOwner);
    }

    protected Owner mapResponse2Domain(CompanyBranchResponse response) {
        return Owner.builder()
                .id(response.getId())
                .name(response.getName())
                .address(response.getAddress())
                .build();
    }

}
