package team.dbe.bms.global.exception;

import lombok.Getter;

@Getter
public class BmsException extends RuntimeException {
    private final ErrorProperty errorProperty;
    public BmsException(ErrorProperty errorProperty) {
        super(errorProperty.getMessage());
        this.errorProperty = errorProperty;
    }
}
