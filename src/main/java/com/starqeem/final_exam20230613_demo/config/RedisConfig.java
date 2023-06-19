package com.starqeem.final_exam20230613_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
/**
 * @Date: 2023/5/17 18:05
 * @author: Qeem
 */
@Configuration
public class RedisConfig {
    /**
     * 配置RedisTemplate的序列化方式
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(factory);
        // 指定key的序列化方式：string
        redisTemplate.setKeySerializer(RedisSerializer.string());
        // 指定value的序列化方式：json
        redisTemplate.setValueSerializer(RedisSerializer.json());
        return redisTemplate;
    }
    /**
     * 配置Redis缓存注解的value序列化方式
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(){
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        configuration = configuration.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                        RedisSerializer.json()
                )
        );
        return configuration;
    }
}
