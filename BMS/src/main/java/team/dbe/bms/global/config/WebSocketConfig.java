package team.dbe.bms.global.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.socket.config.annotation.*;
import team.dbe.bms.domain.device.service.SensorWebSocketHandler;

@Configurable
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final SensorWebSocketHandler sensorWebSocketHandler;

    public WebSocketConfig(SensorWebSocketHandler sensorWebSocketHandler) {
        this.sensorWebSocketHandler = sensorWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(sensorWebSocketHandler, "/ws/sensor")
                .setAllowedOriginPatterns("*");
    }
}
