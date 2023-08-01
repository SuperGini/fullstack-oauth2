package com.gini.mapper;

import com.gini.dto.request.PartRequest;
import com.gini.dto.response.PartResponse;
import com.gini.model.Car;
import com.gini.model.Part;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PartMapper {

    public Part mapFrom(PartRequest partRequest) {
        Car car = new Car();
        if (partRequest.carRequest() != null) {
            car.setCarModel(partRequest.carRequest().carModel());
            car.setManufacturer(partRequest.carRequest().manufacturer());
        }

        return Part.builder()
                .partName(partRequest.partName())
                .car(car)
                .price(partRequest.price())
                .build();
    }

    public PartResponse mapForm(Part part) {
        return new PartResponse(
                part.getId(),
                part.getPartName(),
                part.getPrice(),
                part.getCar()
        );
    }

}
