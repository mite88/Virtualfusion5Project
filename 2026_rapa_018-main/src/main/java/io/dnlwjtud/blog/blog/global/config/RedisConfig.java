package io.dnlwjtud.blog.blog.global.config;

import io.dnlwjtud.blog.blog.global.model.AiJob;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${REDIS_HOST}")
    private String redisHost;

    @Value("${REDIS_PORT}")
    private int redisPort;

    // REDIS_PASSWORD가 없거나 비어있을 경우 빈 문자열("")을 기본값으로 사용
    @Value("${REDIS_PASSWORD:}")
    private String redisPassword;

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

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    // 2. 큐(Job ID) 관리용 템플릿 (String 전용)
    @Bean
    public RedisTemplate<String, String> queueRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

        template.afterPropertiesSet();
        return template;
    }

    // 3. Lettuce 전용 ConnectionFactory (Redisson과 분리)
    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisPort);
        if (redisPassword != null && !redisPassword.isBlank()) {
            config.setPassword(redisPassword);
        }
        return new LettuceConnectionFactory(config);
    }

    // 4. RefreshToken 등 단순 String 저장용 - Lettuce 기반
    @Bean
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(lettuceConnectionFactory);
        template.afterPropertiesSet();
        return template;
    }

    // 5. RedissonClient 빈 정의
    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
              .setAddress("redis://" + redisHost + ":" + redisPort);

        // 비밀번호가 null이 아니고 비어있지 않을 때만 비밀번호를 설정
        if (redisPassword != null && !redisPassword.isBlank()) {
            config.useSingleServer().setPassword(redisPassword);
        }
        // redisPassword가 null이거나 비어있으면 setPassword()를 호출하지 않음
        return Redisson.create(config);
    }
}