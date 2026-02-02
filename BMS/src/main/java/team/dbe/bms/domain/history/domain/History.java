package team.dbe.bms.domain.history.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class History {
    private LocalDateTime time;

    private Long deviceId;

    private Double temperature;

    private Double risk;
}
