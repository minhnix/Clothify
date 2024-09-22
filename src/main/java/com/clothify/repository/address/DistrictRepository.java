package com.clothify.repository.address;

import com.clothify.domain.address.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DistrictRepository extends JpaRepository<District, UUID> {
    @Query(
            value =
                    "SELECT d.* FROM district d "
                            + "WHERE d.city_id = :cityId "
                            + "ORDER BY d.name",
            nativeQuery = true
    )
    List<District> getDistrictByCityId(UUID cityId);

    District findByName(String districtName);

    boolean existsByName(String stringCellValue);
}
