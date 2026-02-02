package team.dbe.bms.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.dbe.bms.domain.device.domain.Device;
import team.dbe.bms.domain.device.domain.repository.JpaDeviceRepository;
import team.dbe.bms.domain.device.exception.DeviceNotFoundException;
import team.dbe.bms.domain.history.presentation.dto.request.SensorDataRequestDto;
import team.dbe.bms.domain.history.domain.History;
import team.dbe.bms.domain.history.domain.repository.HistoryRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HistorySaveService {
    private final JpaDeviceRepository deviceRepository;
    private final HistoryRepository historyRepository;

    public History addHistory(SensorDataRequestDto request) {
        Device device = deviceRepository.findBySerialNumber(request.serialNumber()).orElseThrow(DeviceNotFoundException::new);
        History history = new History(LocalDateTime.now(), device.getId(), request.temperature(), request.risk());
        String key = "device:" + request.serialNumber();
        historyRepository.add(key, history);
        return history;
    }
}
