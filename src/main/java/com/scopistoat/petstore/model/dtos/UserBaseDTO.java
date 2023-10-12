package com.scopistoat.petstore.model.dtos;

import com.scopistoat.petstore.model.Money;

import java.util.Objects;

public class UserBaseDTO {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private Money budget;

    public UserBaseDTO() {
    }

    public UserBaseDTO(String firstName, String lastName, String emailAddress, Money budget) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.budget = budget;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Money getBudget() {
        return budget;
    }

    public void setBudget(Money budget) {
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBaseDTO that = (UserBaseDTO) o;
        return Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(emailAddress, that.emailAddress)
                && Objects.equals(budget, that.budget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, emailAddress, budget);
    }
}
