package com.clothify.service;

import com.clothify.domain.address.City;
import com.clothify.domain.address.District;
import com.clothify.domain.address.Ward;
import com.clothify.payload.response.address.AddressResponse;
import com.clothify.repository.address.CityRepository;
import com.clothify.repository.address.DistrictRepository;
import com.clothify.repository.address.WardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class AddressService {
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;

    public City getCity(UUID cityId) {
        return cityRepository.findById(cityId).orElse(null);
    }

    public District getDistrict(UUID districtId) {
        return districtRepository.findById(districtId).orElse(null);
    }

    public Ward getWard(UUID wardId) {
        return wardRepository.findById(wardId).orElse(null);
    }

    public UUID getCityId(String cityName) {
        return cityRepository.findByName(cityName).getId();
    }

    public UUID getDistrictId(String districtName) {
        return districtRepository.findByName(districtName).getId();
    }

    public List<AddressResponse> getAllCity() {
        return cityRepository.findAllOrderByName().stream().map(AddressResponse::toResponse).toList();
    }

    public List<AddressResponse> getDistrictsByCityId(UUID cityId) {
        return districtRepository.getDistrictByCityId(cityId).stream()
                .map(AddressResponse::toResponse)
                .toList();
    }

    public List<AddressResponse> getWardSByDistrictId(UUID districtId) {
        return wardRepository.getWardByDistrictId(districtId).stream()
                .map(AddressResponse::toResponse)
                .toList();
    }
}
