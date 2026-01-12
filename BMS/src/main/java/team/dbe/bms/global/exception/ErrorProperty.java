package team.dbe.bms.global.exception;

import org.springframework.http.HttpStatus;

public interface ErrorProperty {

    HttpStatus getStatus();

    String getMessage();
}
