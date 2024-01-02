package com.as.users.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {
    private final Environment env;

    public CacheConfig(Environment env) {
        this.env = env;
    }


    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        RedisCacheManager rcm  = RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(redisCacheConfiguration())
                .transactionAware()
                .build();
        return  rcm;
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(env.getProperty("spring.redis.host"));
        redisStandaloneConfiguration.setPort(env.getProperty("spring.redis.port",Integer.class));
        redisStandaloneConfiguration.setPassword(RedisPassword.of(env.getProperty("spring.redis.password")));
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(60))
                .disableCachingNullValues();
        return cacheConfiguration;
    }
}
