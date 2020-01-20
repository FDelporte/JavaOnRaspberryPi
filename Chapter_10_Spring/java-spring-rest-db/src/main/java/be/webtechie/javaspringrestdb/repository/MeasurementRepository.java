package be.webtechie.javaspringrestdb.repository;

import be.webtechie.javaspringrestdb.entity.MeasurementEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<MeasurementEntity, Long>{

    Page<MeasurementEntity> findAll(Pageable pageable);
}