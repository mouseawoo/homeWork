package com.example.homw.service;

import com.example.homw.dao.CarDao;
import com.example.homw.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    CarDao carDao;

    @Autowired
    public CarServiceImpl(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    public Car carIsReserved(Long id) {
        return carDao.carIsReserved(id);
    }

    @Override
    public List<Car> getListCars() {
        return carDao.getListCars();
    }
}
