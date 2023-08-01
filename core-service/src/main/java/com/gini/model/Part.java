package com.gini.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "parts", indexes = @Index( name = "partname_index",columnList = "partName"))
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    private String partName;

    @Column(name = "price")
    private BigDecimal price;

    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "part")
    private Car car;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return Objects.equals(id, part.id) && Objects.equals(partName, part.partName) && Objects.equals(price, part.price) && Objects.equals(car, part.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, partName, price, car);
    }
}
