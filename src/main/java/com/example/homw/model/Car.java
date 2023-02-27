package com.example.homw.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "CAR_TABLE")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "ISRESERVED")
    private boolean isReserved;

    public Car() {
    }

    public Car(String name, boolean isReserved) {
        this.name = name;
        this.isReserved = isReserved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return isReserved == car.isReserved && Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, isReserved);
    }
}
