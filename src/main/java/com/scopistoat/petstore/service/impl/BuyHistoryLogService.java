package com.scopistoat.petstore.service.impl;

import com.scopistoat.petstore.model.BuyHistoryLog;
import com.scopistoat.petstore.model.dtos.BuyHistoryLogDTO;
import com.scopistoat.petstore.repository.IBuyHistoryLogRepository;
import com.scopistoat.petstore.service.IBuyHistoryLogService;
import com.scopistoat.petstore.service.mapper.BuyHistoryLogDTOMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuyHistoryLogService implements IBuyHistoryLogService {
    private final IBuyHistoryLogRepository buyHistoryLogRepository;
    private final BuyHistoryLogDTOMapper buyHistoryLogDTOMapper;

    public BuyHistoryLogService(
            IBuyHistoryLogRepository buyHistoryLogRepository,
            BuyHistoryLogDTOMapper buyHistoryLogDTOMapper) {
        this.buyHistoryLogRepository = buyHistoryLogRepository;
        this.buyHistoryLogDTOMapper = buyHistoryLogDTOMapper;
    }

    @Override
    public List<BuyHistoryLogDTO> findAll() {
        List<BuyHistoryLog> buyHistoryLogs = buyHistoryLogRepository.findAll();
        List<BuyHistoryLogDTO> buyHistoryLogDTOS = buyHistoryLogs.stream()
                .map(buyHistoryLogDTOMapper)
                .collect(Collectors.toList());

        return buyHistoryLogDTOS;
    }
}
