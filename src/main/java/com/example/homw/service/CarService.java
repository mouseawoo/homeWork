package com.example.homw.service;

import com.example.homw.model.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    Car carIsReserved(Long id);
    List<Car> getListCars();
}
