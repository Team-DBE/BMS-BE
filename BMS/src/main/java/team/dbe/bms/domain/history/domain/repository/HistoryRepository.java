package team.dbe.bms.domain.history.domain.repository;

import team.dbe.bms.domain.history.domain.History;

import java.time.Duration;
import java.util.Set;

public interface HistoryRepository {
    void removeDataOlderThan(String key, Duration retention);
    Set<Object> findDataOlderThan(String key, Duration retention);
    void setTTL(String key, Duration retention);
    void add(String key, History history);
}