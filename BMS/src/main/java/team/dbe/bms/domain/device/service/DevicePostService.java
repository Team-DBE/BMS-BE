package team.dbe.bms.domain.device.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import team.dbe.bms.domain.device.domain.Device;
import team.dbe.bms.domain.device.domain.repository.JpaDeviceRepository;
import team.dbe.bms.domain.device.presentation.dto.request.DevicePostRequestDto;

@Service
@RequiredArgsConstructor
public class DevicePostService {
    private final JpaDeviceRepository deviceRepository;

    public void save(DevicePostRequestDto dto) {
        deviceRepository.save(Device.builder()
                .isConnected(false)
                .serialNumber(dto.getSerialNumber()).build());
    }
}
