package team.dbe.bms.domain.history.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import team.dbe.bms.domain.history.presentation.dto.request.SensorDataRequestDto;
import team.dbe.bms.domain.history.service.HistoryLifecycleService;
import team.dbe.bms.global.exception.BmsException;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class SensorWebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final SimpMessagingTemplate messagingTemplate;
    private final HistoryLifecycleService historyLifecycleService;

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message) throws IOException {
        String payload = message.getPayload();

        try {
            SensorDataRequestDto request = objectMapper.readValue(payload, SensorDataRequestDto.class);
            log.info("데이터 수신 - device: {}, temp: {}, risk: {}", request.serialNumber(), request.temperature(), request.risk());

                messagingTemplate.convertAndSend("/sub/device/" + request.serialNumber(), historyLifecycleService.processDeviceHistory(request));
            log.info("데이터 송신 - device: {}, temp: {}, risk: {}", request.serialNumber(), request.temperature(), request.risk());
        } catch (BmsException e) {
            log.warn(e.getMessage());
            session.close(CloseStatus.BAD_DATA);
        } catch (JacksonException e) {
            log.error("JSON 포맷 오류 (파싱 실패): payload={}", payload, e);
            session.close(CloseStatus.BAD_DATA);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }
}
