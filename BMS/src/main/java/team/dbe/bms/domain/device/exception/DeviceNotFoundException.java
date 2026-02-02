package team.dbe.bms.domain.device.exception;

import team.dbe.bms.global.exception.BmsException;

public class DeviceNotFoundException extends BmsException {

    public DeviceNotFoundException() {
        super(DeviceErrorCode.DEVICE_NOT_FOUND);
    }
}
