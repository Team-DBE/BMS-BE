package team.dbe.bms.domain.device.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import team.dbe.bms.domain.device.presentation.dto.request.DevicePostRequestDto;
import team.dbe.bms.domain.device.service.DeviceGetService;
import team.dbe.bms.domain.device.service.DevicePostService;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
public class DeviceController {
    private final DevicePostService devicePostService;
    private final DeviceGetService deviceGetService;

    @PostMapping
    public ResponseEntity<Void> addDevice(@RequestBody DevicePostRequestDto dto) {
        devicePostService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> checkBySerial(@RequestParam("serialNumber") String serialNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(deviceGetService.existsBySerialNumber(serialNumber));
    }
}
