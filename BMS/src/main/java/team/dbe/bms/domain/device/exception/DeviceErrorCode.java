package team.dbe.bms.domain.device.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import team.dbe.bms.global.exception.ErrorProperty;

@Getter
@AllArgsConstructor
public enum DeviceErrorCode implements ErrorProperty {
    DEVICE_NOT_FOUND(HttpStatus.NOT_FOUND, "device not found");

    private HttpStatus status;
    private String message;
}
