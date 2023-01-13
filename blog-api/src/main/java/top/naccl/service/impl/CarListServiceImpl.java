package top.naccl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.naccl.entity.CarList;
import top.naccl.mapper.CarListMapper;
import top.naccl.service.CarListService;

import java.util.List;

@Service
public class CarListServiceImpl implements CarListService {
    @Autowired
    private CarListMapper carListMapper;


    @Override
    public List<CarList> getAll() {
        return carListMapper.getAll();
    }
}
