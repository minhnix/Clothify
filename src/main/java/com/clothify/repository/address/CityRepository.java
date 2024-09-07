package com.clothify.repository.address;

import com.clothify.domain.address.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID> {


    City findByName(String cityName);

    boolean existsByName(String stringCellValue);

    @Query(
            value =
                    "SELECT c.* FROM city c "
                            + "ORDER BY c.name",
            nativeQuery = true
    )
    List<City> findAllOrderByName();
}
