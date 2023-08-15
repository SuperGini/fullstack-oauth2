package com.gini.mapper;

import com.gini.dto.request.PartRequest;
import com.gini.dto.response.CarResponse;
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
                .quantity(partRequest.quantity())
                .car(car)
                .price(partRequest.price())
                .build();
    }

    public PartResponse mapForm(Part part) {
        var car = new CarResponse(
                part.getCar().getId(),
                part.getCar().getManufacturer(),
                part.getCar().getCarModel()
        );

        return PartResponse.builder()
                .id(part.getId())
                .quantity(part.getQuantity())
                .partName(part.getPartName())
                .price(part.getPrice())
                .car(car)
                .build();
    }

}
