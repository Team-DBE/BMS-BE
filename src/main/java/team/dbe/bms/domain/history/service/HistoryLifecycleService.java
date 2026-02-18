package team.dbe.bms.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.dbe.bms.domain.history.presentation.dto.request.SensorDataRequestDto;
import team.dbe.bms.domain.history.domain.History;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class HistoryLifecycleService {
    private final HistoryRetentionService historyRetentionService;
    private final HistorySaveService historySaveService;

    @Transactional
    public History processDeviceHistory(SensorDataRequestDto request) {

        History history = historySaveService.addHistory(request);
        historyRetentionService.removeDataOlderThan(request, Duration.ofDays(1));
        historyRetentionService.setExpireTime(request, Duration.ofDays(1));
        return history;
    }
}
