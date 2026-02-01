package team.dbe.bms.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HistoryGetService {
    private final RedisTemplate<String, Object> redisTemplate;

    public Set<Object> getAllHistory(String serialNumber) {
        String key = "device:" + serialNumber;
        return redisTemplate.opsForZSet().rangeByScore(key, System.currentTimeMillis() - Duration.ofDays(1).toMillis(), Double.MAX_VALUE);
    }
}
