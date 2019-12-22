package be.webtechie.javaspringrestdb.repository;

import be.webtechie.javaspringrestdb.entity.SensorEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepository extends JpaRepository<SensorEntity, Long>{

    Page<SensorEntity> findAll(Pageable pageable);

    List<SensorEntity> findAllByName(String name);

    SensorEntity findById(@Param("id") long id);
}