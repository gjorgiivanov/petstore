package com.scopistoat.petstore.model.dtos;

import com.scopistoat.petstore.model.enums.Type;

import java.time.LocalDate;

public class PetBaseDTO {
    private String name;
    private Type type;
    private String description;
    private LocalDate dateOfBirth;
    private Integer rating;

    public PetBaseDTO() {
    }

    public PetBaseDTO(String name, Type type, String description, LocalDate dateOfBirth) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
    }

    public PetBaseDTO(String name, Type type, String description, LocalDate dateOfBirth, Integer rating) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
