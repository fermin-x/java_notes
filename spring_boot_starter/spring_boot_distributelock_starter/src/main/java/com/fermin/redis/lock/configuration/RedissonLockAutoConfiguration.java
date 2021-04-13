package com.fermin.redis.lock.configuration;

import com.fermin.redis.configuration.RedissonAutoConfiguration;
import com.fermin.redis.configuration.RedissonConfigurations;
import com.fermin.redis.lock.aop.BusinessKeyProvider;
import com.fermin.redis.lock.aop.LockAspectHandler;
import com.fermin.redis.lock.aop.LockInfoProvider;
import com.fermin.redis.lock.core.LockFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnProperty(prefix = RedissonConfigurations.REDISSON_LOCK_PREFIX, name = "enable", havingValue = "true", matchIfMissing = true)
@AutoConfigureAfter(RedissonAutoConfiguration.class)
@EnableConfigurationProperties(RedissonConfigurations.class)
@Import({LockAspectHandler.class})
public class RedissonLockAutoConfiguration {

    @Bean
    public LockInfoProvider lockInfoProvider() {
        return new LockInfoProvider();
    }

    @Bean
    public BusinessKeyProvider businessKeyProvider() {
        return new BusinessKeyProvider();
    }

    @Bean
    public LockFactory lockFactory() {
        return new LockFactory();
    }
}
