package com.fermin.redis.configuration.strategy;

import com.fermin.redis.configuration.RedissonConfigurations;
import org.redisson.client.codec.Codec;
import org.redisson.config.Config;
import org.springframework.util.StringUtils;


public abstract class RedissonConfigStrategy {

    protected RedissonConfigurations redissonConfigurations;

    public Config createRedissonConfig(RedissonConfigurations redissonConfigurations) {
        return this.createRedissonConfig(commonInitiateConfig(redissonConfigurations), redissonConfigurations);
    }

    private Config commonInitiateConfig(RedissonConfigurations redissonConfigurations) {
        Config config = new Config();
        try {
            config.setCodec((Codec) Class.forName(redissonConfigurations.getCodec()).newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        config.setTransportMode(redissonConfigurations.getTransportMode());
        if (redissonConfigurations.getThreads() != null) {
            config.setThreads(redissonConfigurations.getThreads());
        }
        if (redissonConfigurations.getNettyThreads() != null) {
            config.setNettyThreads(redissonConfigurations.getNettyThreads());
        }
        config.setReferenceEnabled(redissonConfigurations.getReferenceEnabled());
        config.setLockWatchdogTimeout(redissonConfigurations.getLockWatchdogTimeout());
        config.setKeepPubSubOrder(redissonConfigurations.getKeepPubSubOrder());
        config.setDecodeInExecutor(redissonConfigurations.getDecodeInExecutor());
        config.setUseScriptCache(redissonConfigurations.getUseScriptCache());
        config.setMinCleanUpDelay(redissonConfigurations.getMinCleanUpDelay());
        config.setMaxCleanUpDelay(redissonConfigurations.getMaxCleanUpDelay());
        return config;
    }

    /**
     * 根据不同的Redis配置策略创建对应的Config
     *
     * @param redissonConfigurations
     * @return Config
     */
    protected abstract Config createRedissonConfig(Config config, RedissonConfigurations redissonConfigurations);

    protected String prefixAddress(String address) {
        if (!StringUtils.isEmpty(address) && !address.startsWith("redis")) {
            return "redis://" + address;
        }
        return address;
    }
}
