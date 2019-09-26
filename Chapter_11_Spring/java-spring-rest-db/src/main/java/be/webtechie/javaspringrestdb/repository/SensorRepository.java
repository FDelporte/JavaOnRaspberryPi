package be.webtechie.javaspringrestdb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import be.webtechie.javaspringrestdb.entity.SensorEntity;

@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, Long>{

    Page<SensorEntity> findAll(Pageable pageable);

    SensorEntity findById(@Param("id") long id);
}