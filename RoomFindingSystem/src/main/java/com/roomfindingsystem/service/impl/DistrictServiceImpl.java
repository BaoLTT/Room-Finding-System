package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.DistrictEntity;
import com.roomfindingsystem.repository.DistrictRepository;
import com.roomfindingsystem.service.DistrictService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    private final ModelMapper modelMapper;

    public DistrictServiceImpl(DistrictRepository districtRepository, ModelMapper modelMapper) {
        this.districtRepository = districtRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DistrictEntity> getAll() {
        List<DistrictEntity> districts = districtRepository.findAll();
        return districts;
    }
    @Override
    public List<DistrictEntity> getDistrictsByProvince(Integer provinceId) {
        return districtRepository.findByProvinceId(provinceId);
    }

    @Override
    public DistrictEntity getDistrictById(int id) {
        return districtRepository.getDistrictById(id) ;
    }
}
