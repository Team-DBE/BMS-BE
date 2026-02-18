package team.dbe.bms.domain.history.presentation.dto.request;

public record SensorDataRequestDto(
        String serialNumber,
        Double temperature,
        Double risk
) {
}
