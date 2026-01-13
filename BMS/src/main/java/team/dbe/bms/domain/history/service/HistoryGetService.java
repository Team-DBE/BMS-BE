package team.dbe.bms.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import team.dbe.bms.domain.history.domain.History;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryGetService {
    private final RedisTemplate<String, History> redisTemplate;

    public List<History> getAllHistory(Long deviceId) {
        String key = "device:" + deviceId + ":history";
        return redisTemplate.opsForList().range(key, 0, -1);
    }
}
