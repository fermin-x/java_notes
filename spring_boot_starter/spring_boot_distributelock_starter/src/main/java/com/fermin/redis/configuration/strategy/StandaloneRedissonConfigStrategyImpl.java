package com.fermin.redis.configuration.strategy;

import com.fermin.redis.configuration.RedissonConfigurations;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 单机方式Redisson配置
 */
public class StandaloneRedissonConfigStrategyImpl extends RedissonConfigStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(StandaloneRedissonConfigStrategyImpl.class);

    public StandaloneRedissonConfigStrategyImpl(RedissonConfigurations redissonConfigurations) {
        this.redissonConfigurations = redissonConfigurations;
    }

    @Override
    public Config createRedissonConfig(Config config, RedissonConfigurations redissonConfigurations) {
        SingleServerConfig singleServerConfig = config.useSingleServer();
        RedissonConfigurations.SingleServerConfigurations param = redissonConfigurations.getSingleServerConfig();
        singleServerConfig.setAddress(prefixAddress(param.getAddress()));
        singleServerConfig.setConnectionMinimumIdleSize(param.getConnectionMinimumIdleSize());
        singleServerConfig.setConnectionPoolSize(param.getConnectionPoolSize());
        singleServerConfig.setDatabase(param.getDatabase());
        singleServerConfig.setDnsMonitoringInterval(param.getDnsMonitoringInterval());
        singleServerConfig.setSubscriptionConnectionMinimumIdleSize(param.getSubscriptionConnectionMinimumIdleSize());
        singleServerConfig.setSubscriptionConnectionPoolSize(param.getSubscriptionConnectionPoolSize());
        singleServerConfig.setPingTimeout(redissonConfigurations.getPingTimeout());
        singleServerConfig.setClientName(redissonConfigurations.getClientName());
        singleServerConfig.setConnectTimeout(redissonConfigurations.getConnectTimeout());
        singleServerConfig.setIdleConnectionTimeout(redissonConfigurations.getIdleConnectionTimeout());
        singleServerConfig.setKeepAlive(redissonConfigurations.getKeepAlive());
        singleServerConfig.setPassword(redissonConfigurations.getPassword());
        singleServerConfig.setPingConnectionInterval(redissonConfigurations.getPingConnectionInterval());
        singleServerConfig.setRetryAttempts(redissonConfigurations.getRetryAttempts());
        singleServerConfig.setRetryInterval(redissonConfigurations.getRetryInterval());
        singleServerConfig.setSslEnableEndpointIdentification(redissonConfigurations.getSslEnableEndpointIdentification());
        singleServerConfig.setSslKeystore(redissonConfigurations.getSslKeystore());
        singleServerConfig.setSslKeystorePassword(redissonConfigurations.getSslKeystorePassword());
        singleServerConfig.setSslProvider(redissonConfigurations.getSslProvider());
        singleServerConfig.setSslTruststore(redissonConfigurations.getSslTruststore());
        singleServerConfig.setSslTruststorePassword(redissonConfigurations.getSslTruststorePassword());
        singleServerConfig.setSubscriptionsPerConnection(redissonConfigurations.getSubscriptionsPerConnection());
        singleServerConfig.setTcpNoDelay(redissonConfigurations.getTcpNoDelay());
        singleServerConfig.setTimeout(redissonConfigurations.getTimeout());
        return config;
    }
}
