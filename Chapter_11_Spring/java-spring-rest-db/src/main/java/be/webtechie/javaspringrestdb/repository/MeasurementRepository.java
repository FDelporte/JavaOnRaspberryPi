package be.webtechie.javaspringrestdb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import be.webtechie.javaspringrestdb.entity.MeasurementEntity;

@Repository
public interface MeasurementRepository extends JpaRepository<MeasurementEntity, Long>{

    Page<MeasurementEntity> findAll(Pageable pageable);

    MeasurementEntity findById(@Param("id") long id);

    MeasurementEntity findAllBySensorEntityId(@Param("sensorEntityId") long sensorEntityId);
}