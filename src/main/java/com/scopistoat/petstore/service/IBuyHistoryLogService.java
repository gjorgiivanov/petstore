package com.scopistoat.petstore.service;

import com.scopistoat.petstore.model.dtos.BuyHistoryLogDTO;

import java.util.List;

public interface IBuyHistoryLogService {
    List<BuyHistoryLogDTO> findAll();
}
