package team.dbe.bms.domain.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.dbe.bms.domain.device.domain.repository.JpaDeviceRepository;

@Service
@RequiredArgsConstructor
public class DeviceGetService {
    private final JpaDeviceRepository deviceRepository;

    public Boolean existsBySerialNumber(String serialNumber) {
        return deviceRepository.existsBySerialNumber(serialNumber);
    }
}
