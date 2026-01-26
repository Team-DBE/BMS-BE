package team.dbe.bms.domain.device.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import team.dbe.bms.domain.device.domain.Device;
import team.dbe.bms.domain.device.domain.repository.JpaDeviceRepository;
import team.dbe.bms.domain.device.exception.DeviceNotFoundException;
import team.dbe.bms.domain.device.presentation.dto.request.SensorDataRequestDto;
import team.dbe.bms.domain.history.domain.History;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class SensorWebSocketHandler extends TextWebSocketHandler {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;
    private final SimpMessagingTemplate messagingTemplate;
    private final JpaDeviceRepository deviceRepository;

    private static final int HISTORY_TTL_DAYS = 1;

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) {
        String payload = message.getPayload();

        try {
            SensorDataRequestDto request = objectMapper.readValue(payload, SensorDataRequestDto.class);
            Device device = deviceRepository.findBySerialNumber(request.serialNumber()).orElseThrow(DeviceNotFoundException::new);
            log.info("데이터 수신 - device: {}, temp: {}, risk: {}", request.serialNumber(), request.temperature(), request.risk());

            String key = "device:" + request.serialNumber();
            Long currentTime = System.currentTimeMillis();
            History history = new History(LocalDateTime.now(), device.getId(), request.temperature(), request.risk());
            redisTemplate.opsForZSet().add(key, history, currentTime);
            redisTemplate.opsForZSet().removeRangeByScore(key, 0, currentTime - Duration.ofDays(1).toMillis());
            messagingTemplate.convertAndSend("/sub/device/" + request.serialNumber(), history);
            log.info("데이터 송신 - device: {}, temp: {}, risk: {}", request.serialNumber(), request.temperature(), request.risk());
            redisTemplate.expire(key, Duration.ofDays(HISTORY_TTL_DAYS));
        } catch (DeviceNotFoundException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("JSON 파싱 실패 payload: {}", payload, e);
        }
    }
}
