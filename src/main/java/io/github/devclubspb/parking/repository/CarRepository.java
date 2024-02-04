package io.github.devclubspb.parking.repository;

import io.github.devclubspb.parking.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<CarEntity, Long> {

    @Query("from CarEntity order by brand, model")
    List<CarEntity> findAllSortedByModel();

}
