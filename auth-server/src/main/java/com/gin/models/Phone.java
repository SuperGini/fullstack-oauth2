package com.gin.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Phone {

    @Column(name = "phone_Number1", length = 20)
    private String phoneNumber1;

    @Column(name = "phone_Number2", length = 20)
    private String phoneNumber2;
}
