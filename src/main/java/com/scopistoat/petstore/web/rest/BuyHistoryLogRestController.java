package com.scopistoat.petstore.web.rest;

import com.scopistoat.petstore.model.dtos.BuyHistoryLogDTO;
import com.scopistoat.petstore.service.IBuyHistoryLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/history-log")
public class BuyHistoryLogRestController {
    private final IBuyHistoryLogService buyHistoryLogService;

    public BuyHistoryLogRestController(IBuyHistoryLogService buyHistoryLogService) {
        this.buyHistoryLogService = buyHistoryLogService;
    }

    @GetMapping
    public List<BuyHistoryLogDTO> historyLogs() {
        return buyHistoryLogService.findAll();
    }
}
