package com.scopistoat.petstore.service;

import com.scopistoat.petstore.model.BuyHistoryLog;
import com.scopistoat.petstore.model.dtos.BuyHistoryLogDTO;
import com.scopistoat.petstore.repository.IBuyHistoryLogRepository;
import com.scopistoat.petstore.service.impl.BuyHistoryLogService;
import com.scopistoat.petstore.service.mapper.BuyHistoryLogDTOMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuyHistoryLogServiceTest {
    @Mock
    private IBuyHistoryLogRepository buyHistoryLogRepository;

    private final BuyHistoryLogDTOMapper buyHistoryLogDTOMapper = new BuyHistoryLogDTOMapper();

    private IBuyHistoryLogService underTest;

    @BeforeEach
    void setUp() {
        underTest = new BuyHistoryLogService(buyHistoryLogRepository, buyHistoryLogDTOMapper);
    }

    @Test
    void canFindAllHistoryLogs() {
        // when
        underTest.findAll();

        // then
        verify(buyHistoryLogRepository).findAll();
    }

    @Test
    public void canFindHistoryLogs() {
        // given
        List<BuyHistoryLog> buyHistoryLogList = new ArrayList<>();
        buyHistoryLogList.add(new BuyHistoryLog(
                LocalDateTime.now(),
                6,
                4
        ));
        buyHistoryLogList.add(new BuyHistoryLog(
                LocalDateTime.now(),
                3,
                7
        ));

        List<BuyHistoryLogDTO> expectedList = buyHistoryLogList.stream()
                .map(buyHistoryLogDTOMapper)
                .collect(Collectors.toList());

        // when
        when(buyHistoryLogRepository.findAll()).thenReturn(buyHistoryLogList);
        List<BuyHistoryLogDTO> result = underTest.findAll();

        // then
        assertEquals(result, expectedList);
    }
}