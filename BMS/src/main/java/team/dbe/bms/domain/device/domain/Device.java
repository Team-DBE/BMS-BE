package team.dbe.bms.domain.device.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    private String serialNumber;

    private Boolean isConnected;

    @Builder
    public Device(String serialNumber, Boolean isConnected) {
        this.serialNumber = serialNumber;
        this.isConnected = isConnected;
    }
}
