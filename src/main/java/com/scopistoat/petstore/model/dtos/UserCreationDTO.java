package com.scopistoat.petstore.model.dtos;

import com.scopistoat.petstore.model.Money;

public class UserCreationDTO extends UserBaseDTO {
    public UserCreationDTO() {
    }

    public UserCreationDTO(String firstName, String lastName, String emailAddress, Money budget) {
        super(firstName, lastName, emailAddress, budget);
    }
}
