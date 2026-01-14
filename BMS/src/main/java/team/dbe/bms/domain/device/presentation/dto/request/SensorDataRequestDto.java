package team.dbe.bms.domain.device.presentation.dto.request;

public record SensorDataRequestDto(
        String serialNumber,
        Double temperature,
        Double risk
) {
}
