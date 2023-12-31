package com.roomfindingsystem.repository;
import com.roomfindingsystem.entity.WardEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SpringBootApplication
public interface WardRepository extends CrudRepository<WardEntity, Integer>, JpaRepository<WardEntity, Integer> {

    List<WardEntity> findByDistrictIdAndProvinceId(Integer districtId, Integer provinceId);

    @Query(value = "SELECT * FROM room_finding_system.ward a where a.wardid = ?1 ",nativeQuery = true)
    WardEntity getWardById(Integer id);
}
