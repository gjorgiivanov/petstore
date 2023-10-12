package com.scopistoat.petstore.model;

import com.scopistoat.petstore.model.enums.Currency;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.Objects;

@Embeddable
public class Money {
    private final double amount;

    @Enumerated(value = EnumType.STRING)
    private final Currency currency;

    protected Money() {
        this.amount = 0.0;
        this.currency = Currency.USD;
    }

    public Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money subtract(Money money) {
        if (!currency.equals(money.currency)) {
            throw new IllegalArgumentException("Cannot subtract two Money objects with different currencies");
        }

        double newAmount = this.amount - money.amount;
        if (newAmount < 0.0) {
            throw new IllegalStateException("Cannot subtract two amounts from Money objects with negative result");
        }

        return new Money(newAmount, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return amount == money.amount && currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
