package com.fermin.redis.configuration.strategy;

import com.fermin.redis.configuration.RedissonConfigurations;
import org.redisson.config.Config;


public class RedissonConfigContext {

    private RedissonConfigStrategy redissonConfigStrategy;

    public RedissonConfigContext(RedissonConfigStrategy _redissonConfigStrategy) {
        this.redissonConfigStrategy = _redissonConfigStrategy;
    }

    /**
     * 上下文根据构造中传入的具体策略产出真实的Redisson的Config
     *
     * @param redissonProperties
     * @return
     */
    public Config createRedissonConfig(RedissonConfigurations redissonProperties) {
        return this.redissonConfigStrategy.createRedissonConfig(redissonProperties);
    }
}
