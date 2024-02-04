package io.github.devclubspb.parking.service;

import io.github.devclubspb.parking.client.CompanyBranchClient;
import io.github.devclubspb.parking.domain.Car;
import io.github.devclubspb.parking.domain.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SingleOwnerEnrichmentService implements OwnerEnrichmentService {

    private final CompanyBranchClient branchClient;

    @Override
    public void enrichOwner(Car car) {
        Long ownerId = car.getOwner().getId();
        branchClient.getBranch(ownerId)
                .map(branch -> Owner.builder()
                        .id(branch.getId())
                        .name(branch.getName())
                        .address(branch.getAddress())
                        .build())
                .ifPresent(car::setOwner);
    }

}
