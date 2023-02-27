package com.example.homw.controller;

import com.example.homw.model.Car;
import com.example.homw.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class Rest {

    CarService carService;

    @Autowired
    public Rest(CarService carService) {
        this.carService = carService;
    }

    /*
    * get запрос на url /api/reserved
    * params id=?; пример: /api/reserved?id=1. id может быть равен только числовому значению*/
    @GetMapping("/reserved")
    public ResponseEntity<?> autoIsReserved(@RequestParam Long id) {
        if (carService.getListCars().size() < id) {
            return new ResponseEntity<>("Car doesn't exist", HttpStatus.NOT_FOUND);
        }
        Car car = carService.carIsReserved(id);
        if (car.isReserved()) {
            return new ResponseEntity<>("The car is reserved", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
