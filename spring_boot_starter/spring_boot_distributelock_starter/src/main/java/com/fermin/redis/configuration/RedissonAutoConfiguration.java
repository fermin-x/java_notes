package com.fermin.redis.configuration;


import com.fermin.redis.configuration.strategy.RedissonClientManager;
import com.fermin.redis.operation.RedissonBinary;
import com.fermin.redis.operation.RedissonCollection;
import com.fermin.redis.operation.RedissonObject;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = RedissonConfigurations.class)
@ConditionalOnClass(RedissonConfigurations.class)
public class RedissonAutoConfiguration {

    @Autowired
    private RedissonConfigurations redissonConfigurations;

    @Bean
    @ConditionalOnMissingBean(RedissonBinary.class)
    public RedissonBinary RedissonBinary() {
        return new RedissonBinary();
    }

    @Bean
    @ConditionalOnMissingBean(RedissonObject.class)
    public RedissonObject RedissonObject() {
        return new RedissonObject();
    }

    @Bean
    @ConditionalOnMissingBean(RedissonCollection.class)
    public RedissonCollection RedissonCollection() {
        return new RedissonCollection();
    }

    @Bean
    @ConditionalOnMissingBean(RedissonClient.class)
    public RedissonClient redissonClient() {
        return new RedissonClientManager(redissonConfigurations).getRedissonClient();
    }

}
