package team.dbe.bms.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.dbe.bms.domain.device.domain.Device;
import team.dbe.bms.domain.device.domain.repository.JpaDeviceRepository;
import team.dbe.bms.domain.device.exception.DeviceNotFoundException;
import team.dbe.bms.domain.history.presentation.dto.request.SensorDataRequestDto;
import team.dbe.bms.domain.history.domain.repository.HistoryRepository;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class HistoryRetentionService {
    private final HistoryRepository historyRepository;
    private final JpaDeviceRepository deviceRepository;

    public void removeDataOlderThan(SensorDataRequestDto request, Duration retention) {
        Device device = deviceRepository.findBySerialNumber(request.serialNumber()).orElseThrow(DeviceNotFoundException::new);
        historyRepository.removeDataOlderThan(request.serialNumber(), retention);
    }

    public void setExpireTime(SensorDataRequestDto request, Duration retention) {
        Device device = deviceRepository.findBySerialNumber(request.serialNumber()).orElseThrow(DeviceNotFoundException::new);
        historyRepository.setTTL(request.serialNumber(), retention);
    }
}
