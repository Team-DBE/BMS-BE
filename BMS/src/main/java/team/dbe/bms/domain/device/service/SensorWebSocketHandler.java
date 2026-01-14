package team.dbe.bms.domain.device.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import team.dbe.bms.domain.device.presentation.dto.request.SensorDataRequestDto;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class SensorWebSocketHandler extends TextWebSocketHandler {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    private static final int HISTORY_TTL_DAYS = 1;

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) {
        String payload = message.getPayload();

        try {
            SensorDataRequestDto request = objectMapper.readValue(payload, SensorDataRequestDto.class);

            log.info("데이터 수신 - device: {}, temp: {}", request.serialNumber(), request.temperature());

            String key = "device:" + request.serialNumber();

            Map<String, Double> sensorData = new HashMap<>();
            sensorData.put("temp", request.temperature());
            sensorData.put("risk", request.risk());

            redisTemplate.opsForHash().putAll(key, sensorData);
            redisTemplate.expire(key, Duration.ofDays(HISTORY_TTL_DAYS));

        } catch (Exception e) {
            log.error("JSON 파싱 실패 payload: {}", payload, e);
        }
    }
}
