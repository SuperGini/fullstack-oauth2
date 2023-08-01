package com.gini.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "cars", indexes = @Index(name = "manufacturer_index", columnList = "manufacturer"))
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "carModel")
    private String carModel;

    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "car")
    private Part part;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) && Objects.equals(manufacturer, car.manufacturer) && Objects.equals(carModel, car.carModel) && Objects.equals(part, car.part);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, manufacturer, carModel, part);
    }
}
