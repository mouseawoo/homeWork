package com.example.homw.dao;

import com.example.homw.model.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    @PersistenceContext
    private EntityManager em;

    public Car carIsReserved(Long id) {
        return em.createQuery("from Car where id = :idParam", Car.class)
                .setParameter("idParam", id).getSingleResult();
    }

    @Override
    public List<Car> getListCars() {
        return em.createQuery("from Car c", Car.class).getResultList();
    }
}
