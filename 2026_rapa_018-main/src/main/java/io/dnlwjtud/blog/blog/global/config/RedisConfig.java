package io.dnlwjtud.blog.blog.global.config;

import io.dnlwjtud.blog.blog.global.model.AiJob;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    // 1. AiJob 객체 저장용 템플릿
    @Bean
    public RedisTemplate<String, AiJob> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, AiJob> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        JacksonJsonRedisSerializer<AiJob> serializer =
                new JacksonJsonRedisSerializer<>(AiJob.class);

        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(serializer);
        template.setHashKeySerializer(RedisSerializer.string());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();

        return template;
    }

    // 2. 큐(Job ID) 관리용 템플릿 (String 전용)
    @Bean
    public RedisTemplate<String, String> queueRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(RedisSerializer.string());
        template.setValueSerializer(RedisSerializer.string());

        template.afterPropertiesSet();
        return template;
    }


}