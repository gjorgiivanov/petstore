package com.scopistoat.petstore.model;

import com.scopistoat.petstore.model.enums.Type;
import jakarta.persistence.Entity;

import java.time.LocalDate;

import static com.scopistoat.petstore.constant.PetConstants.MULTIPLIER_MONEY;

@Entity
public class Dog extends Pet {
    private int rating;

    public Dog() {
    }

    public Dog(String name, Type type, String description, LocalDate dateOfBirth, int rating) {
        super(name, type, description, dateOfBirth);
        this.rating = rating;
        setPrice(calculatedPrice());
    }

    @Override
    public Money calculatedPrice() {
        double amount = (getAgeInYears() * MULTIPLIER_MONEY.getAmount()) + (MULTIPLIER_MONEY.getAmount() * this.rating);
        return new Money(amount, MULTIPLIER_MONEY.getCurrency());
    }

    @Override
    public String successfullyBoughtMessage() {
        return "Woof, dog " + getName() + " has owner " + getOwner().getFullName();
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
