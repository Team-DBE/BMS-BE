package team.dbe.bms.global.exception;

import lombok.Builder;

@Builder
public record ErrorResponseDto(
        int status,
        String error,
        String message
) {
}
