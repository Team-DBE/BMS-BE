package team.dbe.bms.domain.history.domain.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import team.dbe.bms.domain.history.domain.History;

import java.time.Duration;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepository {
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void removeDataOlderThan(String key, Duration retention) {
        redisTemplate.opsForZSet().removeRangeByScore(key, 0, System.currentTimeMillis() - Duration.ofDays(1).toMillis());
    }

    @Override
    public Set<Object> findDataOlderThan(String key, Duration retention) {
        return redisTemplate.opsForZSet().rangeByScore(key, System.currentTimeMillis() - Duration.ofDays(1).toMillis(), Double.MAX_VALUE);
    }

    @Override
    public void setTTL(String key, Duration retention) {
        redisTemplate.expire(key, retention);
    }

    @Override
    public void add(String key, History history) {
        redisTemplate.opsForZSet().add(key, history, System.currentTimeMillis());
    }
}
