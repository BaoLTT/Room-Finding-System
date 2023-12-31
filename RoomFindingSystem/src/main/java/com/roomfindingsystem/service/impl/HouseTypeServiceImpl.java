package com.roomfindingsystem.service.impl;

import com.roomfindingsystem.entity.TypeHouseEntity;
import com.roomfindingsystem.repository.HouseTypeRepository;
import com.roomfindingsystem.service.HouseTypeService;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("houseTypeService")
public class HouseTypeServiceImpl implements HouseTypeService {
    private HouseTypeRepository houseTypeRepository;

    public HouseTypeServiceImpl(HouseTypeRepository houseTypeRepository){
        super();
        this.houseTypeRepository = houseTypeRepository;
    }
    @Override
    public List<TypeHouseEntity> findAll() {
        return  houseTypeRepository.findAll();
    }

    @Override
    public List<TypeHouseEntity> findTypeNotUse() {
        return houseTypeRepository.findTypeNotUse();
    }

    @Override
    public void deleteType(Integer typeid) {
        houseTypeRepository.deleteById(typeid);
    }

    @Override
    public void addType(TypeHouseEntity typeHouseEntity) {
        houseTypeRepository.save(typeHouseEntity);
    }
}
