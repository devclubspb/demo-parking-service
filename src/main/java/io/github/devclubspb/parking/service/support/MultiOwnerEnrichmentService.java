package io.github.devclubspb.parking.service.support;

import io.github.devclubspb.parking.client.CompanyBranchClient;
import io.github.devclubspb.parking.domain.Car;
import io.github.devclubspb.parking.domain.Owner;
import io.github.devclubspb.parking.service.OwnerEnrichmentService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@ConditionalOnProperty(name = "application.enrichment-mode", havingValue = "multi")
public class MultiOwnerEnrichmentService
        extends SingleOwnerEnrichmentService
        implements OwnerEnrichmentService {

    public MultiOwnerEnrichmentService(CompanyBranchClient branchClient) {
        super(branchClient);
    }

    @Override
    public void enrichOwners(Collection<Car> cars) {
        Set<Long> ids = cars.stream().map(Car::getOwner).map(Owner::getId).collect(Collectors.toSet());
        Map<Long, Owner> ownerById = branchClient.getBranches(ids).stream()
                .map(this::mapResponse2Domain)
                .collect(Collectors.toMap(Owner::getId, Function.identity()));
        for (Car car : cars) {
            Owner owner = ownerById.get(car.getOwner().getId());
            if (owner != null) {
                car.setOwner(owner);
            }
        }
    }

}
