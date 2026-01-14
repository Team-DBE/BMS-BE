package team.dbe.bms.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryGetService {
    private final RedisTemplate<String, Object> redisTemplate;

    public List<Object> getAllHistory(String serialNumber) {
        String key = "device:" + serialNumber;
        return redisTemplate.opsForList().range(key, 0, -1);
    }
}
