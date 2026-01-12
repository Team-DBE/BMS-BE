package team.dbe.bms.domain.history.domain;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Persistable;
import team.dbe.bms.domain.device.domain.Device;

import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@IdClass(HistoryId.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class History implements Persistable<HistoryId> {
    @Id
    private LocalDateTime time;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    private Device device;

    private Double temperature;

    @Override
    public @Nullable HistoryId getId() {
        return new HistoryId(this.time, this.device.getId());
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
