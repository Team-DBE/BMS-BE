package team.dbe.bms.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryGetService {
    private final RedisTemplate<String, Object> redisTemplate;

    public List<Object> getAllHistory(Long deviceId) {
        String key = "device:" + deviceId + ":history";
        return redisTemplate.opsForList().range(key, 0, -1);
    }
}
