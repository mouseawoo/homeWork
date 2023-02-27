package com.example.homw.dao;

import com.example.homw.model.Car;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDao {

    Car carIsReserved(Long id);
    List<Car> getListCars();

}
