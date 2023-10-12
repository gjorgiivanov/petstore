package com.scopistoat.petstore.model.dtos;

import com.scopistoat.petstore.model.enums.Type;

import java.time.LocalDate;

public class PetCreationDTO extends PetBaseDTO {
    public PetCreationDTO() {
    }

    public PetCreationDTO(String name, Type type, String description, LocalDate dateOfBirth) {
        super(name, type, description, dateOfBirth);
    }

    public PetCreationDTO(String name, Type type, String description, LocalDate dateOfBirth, int rating) {
        super(name, type, description, dateOfBirth, rating);
    }
}
