package com.scopistoat.petstore.model.dtos;

import com.scopistoat.petstore.model.Money;

public class UserDTO extends UserBaseDTO {
    public UserDTO() {
    }

    public UserDTO(String firstName, String lastName, String emailAddress, Money budget) {
        super(firstName, lastName, emailAddress, budget);
    }
}
