package team.dbe.bms.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import team.dbe.bms.domain.history.domain.History;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, History> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, History> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new JacksonJsonRedisSerializer<>(History.class));

        return template;
    }
}
