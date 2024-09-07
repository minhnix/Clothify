package com.clothify.repository.address;

import com.clothify.domain.address.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WardRepository extends JpaRepository<Ward, UUID> {
    @Query(
            value =
                    "SELECT w.* FROM ward w "
                            + "WHERE w.district_id = :districtId "
                            + "ORDER BY w.name",
            nativeQuery = true
    )
    List<Ward> getWardByDistrictId(UUID districtId);

    Ward findByName(String wardName);

    boolean existsByName(String stringCellValue);
}
