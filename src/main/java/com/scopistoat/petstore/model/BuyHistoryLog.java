package com.scopistoat.petstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class BuyHistoryLog {
    @Id
    private LocalDateTime dateOfExecution;

    @Column(nullable = false)
    private int numberOfSuccessfulBuyers;

    @Column(nullable = false)
    private int numberOfUnsuccessfulBuyers;

    public BuyHistoryLog() {
    }

    public BuyHistoryLog(
            LocalDateTime dateOfExecution,
            int numberOfSuccessfulBuyers,
            int numberOfUnsuccessfulBuyers) {
        this.dateOfExecution = dateOfExecution;
        this.numberOfSuccessfulBuyers = numberOfSuccessfulBuyers;
        this.numberOfUnsuccessfulBuyers = numberOfUnsuccessfulBuyers;
    }

    public LocalDateTime getDateOfExecution() {
        return dateOfExecution;
    }

    public void setDateOfExecution(LocalDateTime dateOfExecution) {
        this.dateOfExecution = dateOfExecution;
    }

    public int getNumberOfSuccessfulBuyers() {
        return numberOfSuccessfulBuyers;
    }

    public void setNumberOfSuccessfulBuyers(int numberOfSuccessfulBuyers) {
        this.numberOfSuccessfulBuyers = numberOfSuccessfulBuyers;
    }

    public int getNumberOfUnsuccessfulBuyers() {
        return numberOfUnsuccessfulBuyers;
    }

    public void setNumberOfUnsuccessfulBuyers(int numberOfUnsuccessfulBuyers) {
        this.numberOfUnsuccessfulBuyers = numberOfUnsuccessfulBuyers;
    }
}
