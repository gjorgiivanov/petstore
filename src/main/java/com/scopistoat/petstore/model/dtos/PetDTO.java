package com.scopistoat.petstore.model.dtos;

import com.scopistoat.petstore.model.Money;
import com.scopistoat.petstore.model.enums.Type;

import java.time.LocalDate;
import java.util.Objects;

public class PetDTO extends PetBaseDTO {
    private Money price;
    private UserDTO owner;

    public PetDTO() {
    }

    public PetDTO(String name, Type type, String description, LocalDate dateOfBirth, Money price, UserDTO owner) {
        super(name, type, description, dateOfBirth);
        this.price = price;
        this.owner = owner;
    }

    public PetDTO(
            String name,
            Type type,
            String description,
            LocalDate dateOfBirth,
            Integer rating,
            Money price,
            UserDTO owner) {
        super(name, type, description, dateOfBirth, rating);
        this.price = price;
        this.owner = owner;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public UserDTO getOwner() {
        return owner;
    }

    public void setOwner(UserDTO owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetDTO petDTO = (PetDTO) o;
        return price.equals(petDTO.price) && Objects.equals(owner, petDTO.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, owner);
    }
}
