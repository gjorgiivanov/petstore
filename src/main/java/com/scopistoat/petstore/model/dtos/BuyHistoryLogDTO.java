package com.scopistoat.petstore.model.dtos;

import java.time.LocalDateTime;
import java.util.Objects;

public class BuyHistoryLogDTO {
    private LocalDateTime dateOfExecution;
    private int numberOfSuccessfulBuyers;
    private int numberOfUnsuccessfulBuyers;

    public BuyHistoryLogDTO() {
    }

    public BuyHistoryLogDTO(
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyHistoryLogDTO that = (BuyHistoryLogDTO) o;
        return numberOfSuccessfulBuyers == that.numberOfSuccessfulBuyers
                && numberOfUnsuccessfulBuyers == that.numberOfUnsuccessfulBuyers
                && Objects.equals(dateOfExecution, that.dateOfExecution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateOfExecution, numberOfSuccessfulBuyers, numberOfUnsuccessfulBuyers);
    }
}
