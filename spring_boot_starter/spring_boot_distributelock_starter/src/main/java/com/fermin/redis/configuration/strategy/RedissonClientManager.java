package com.fermin.redis.configuration.strategy;

import com.fermin.redis.configuration.RedissonConfigurations;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Redisson核心配置，用于提供初始化的redisson实例
 */
public class RedissonClientManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(Redisson.class);

    private Config config;

    private RedissonClient redissonClient;

    public RedissonClientManager(RedissonConfigurations redissonProperties) {
        try {
            config = RedissonConfigFactory.getInstance().createConfig(redissonProperties);
            redissonClient = Redisson.create(config);
        } catch (Exception e) {
            LOGGER.error("Redisson init error", e);
            throw new IllegalArgumentException("please input correct configurations");
        }
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }

    /**
     * Redisson连接方式配置工厂
     * 双重检查锁
     */
    static class RedissonConfigFactory {

        private static volatile RedissonConfigFactory factory = null;

        private RedissonConfigFactory() {
        }

        public static RedissonConfigFactory getInstance() {
            if (factory == null) {
                synchronized (Object.class) {
                    if (factory == null) {
                        factory = new RedissonConfigFactory();
                    }
                }
            }
            return factory;
        }

        /**
         * 根据连接类型获取对应连接方式的配置,基于策略模式
         *
         * @param redissonProperties
         * @return Config
         */
        Config createConfig(RedissonConfigurations redissonProperties) {

            //声明配置上下文
            RedissonConfigContext redissonConfigContext;
            switch (redissonProperties.getModel()) {
                case SINGLE:
                    redissonConfigContext = new RedissonConfigContext(new StandaloneRedissonConfigStrategyImpl(redissonProperties));
                    break;
                case CLUSTER:
                    redissonConfigContext = new RedissonConfigContext(new ClusterRedissonConfigStrategyImpl(redissonProperties));
                    break;
                case SENTINEL:
                    redissonConfigContext = new RedissonConfigContext(new SentinelRedissonConfigStrategyImpl(redissonProperties));
                    break;
                case MASTERSLAVE:
                    redissonConfigContext = new RedissonConfigContext(new MasterslaveRedissonConfigStrategyImpl(redissonProperties));
                    break;
                case REPLICATED:
                    redissonConfigContext = new RedissonConfigContext(new ReplicatedRedissonConfigStrategyImpl(redissonProperties));
                    break;
                default:
                    throw new IllegalArgumentException("创建Redisson连接Config失败，不支持当前连接方式:" + redissonProperties.getModel());
            }

            return redissonConfigContext.createRedissonConfig(redissonProperties);
        }
    }

}
