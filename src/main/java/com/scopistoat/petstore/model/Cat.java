package com.scopistoat.petstore.model;

import com.scopistoat.petstore.model.enums.Type;
import jakarta.persistence.Entity;

import java.time.LocalDate;

import static com.scopistoat.petstore.constant.PetConstants.MULTIPLIER_MONEY;

@Entity
public class Cat extends Pet {
    public Cat() {
    }

    public Cat(String name, Type type, String description, LocalDate dateOfBirth) {
        super(name, type, description, dateOfBirth);
        setPrice(calculatedPrice());
    }

    @Override
    public Money calculatedPrice() {
        return new Money(getAgeInYears() * MULTIPLIER_MONEY.getAmount(), MULTIPLIER_MONEY.getCurrency());
    }

    @Override
    public String successfullyBoughtMessage() {
        return "Meow, cat " + getName() + " has owner " + getOwner().getFullName();
    }
}
