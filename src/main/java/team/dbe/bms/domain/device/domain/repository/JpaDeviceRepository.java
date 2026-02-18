package team.dbe.bms.domain.device.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.dbe.bms.domain.device.domain.Device;

import java.util.Optional;

public interface JpaDeviceRepository extends JpaRepository<Device, Long> {
    Boolean existsBySerialNumber(String serialNumber);

    Optional<Device> findBySerialNumber(String serialNumber);
}
