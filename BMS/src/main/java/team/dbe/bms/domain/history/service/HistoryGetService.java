package team.dbe.bms.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import team.dbe.bms.domain.history.domain.repository.HistoryRepository;

import java.time.Duration;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class HistoryGetService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final HistoryRepository historyRepository;


    public Set<Object> getAllHistory(String serialNumber) {
        String key = "device:" + serialNumber;
        return historyRepository.findDataOlderThan(key, Duration.ofDays(1));
    }
}
