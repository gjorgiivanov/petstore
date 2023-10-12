package com.scopistoat.petstore.model;

import com.scopistoat.petstore.model.enums.Type;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User owner;

    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    private Money price;

    public Pet() {
    }

    public Pet(String name, Type type, String description, LocalDate dateOfBirth) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
    }

    public abstract Money calculatedPrice();
    public abstract String successfullyBoughtMessage();

    public int getAgeInYears() {
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(this.dateOfBirth, currentDate);

        return period.getYears();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
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

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
