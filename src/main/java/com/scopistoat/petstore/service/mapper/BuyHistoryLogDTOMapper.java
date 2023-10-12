package com.scopistoat.petstore.service.mapper;

import com.scopistoat.petstore.model.BuyHistoryLog;
import com.scopistoat.petstore.model.dtos.BuyHistoryLogDTO;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class BuyHistoryLogDTOMapper implements Function<BuyHistoryLog, BuyHistoryLogDTO> {
    @Override
    public BuyHistoryLogDTO apply(BuyHistoryLog buyHistoryLog) {
        return new BuyHistoryLogDTO(
                buyHistoryLog.getDateOfExecution(),
                buyHistoryLog.getNumberOfSuccessfulBuyers(),
                buyHistoryLog.getNumberOfUnsuccessfulBuyers()
        );
    }
}
