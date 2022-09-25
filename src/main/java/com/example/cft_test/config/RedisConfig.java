package com.example.cft_test.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
@EnableCaching
public class RedisConfig {
    @Bean
    public JedisConnectionFactory connectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("localhost");
        configuration.setPort(6000);
//        configuration.setPassword("8n+QI13vneDUa96Adzc29foUOynNfB26B9lHSUDZ5dn8o4PXWlNkojf30tHOW96tEIknBhOyQw==");
        configuration.setPassword("");

        return new JedisConnectionFactory(configuration);
    }
}
