package com.fermin.redis.configuration.strategy;


import com.fermin.redis.configuration.RedissonConfigurations;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.connection.balancer.LoadBalancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 哨兵集群方式Redis连接配置
 */
public class SentinelRedissonConfigStrategyImpl extends RedissonConfigStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(SentinelRedissonConfigStrategyImpl.class);

    public SentinelRedissonConfigStrategyImpl(RedissonConfigurations redissonConfigurations) {
        this.redissonConfigurations = redissonConfigurations;
    }

    @Override
    public Config createRedissonConfig(Config config, RedissonConfigurations redissonConfigurations) {
        RedissonConfigurations.MultipleServerConfigurations multipleServerConfigurations = redissonConfigurations.getMultipleServerConfig();
        SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
        sentinelServersConfig.setDatabase(multipleServerConfigurations.getDatabase());
        sentinelServersConfig.setMasterName(multipleServerConfigurations.getMasterName());
        sentinelServersConfig.setScanInterval(multipleServerConfigurations.getScanInterval());
        sentinelServersConfig.setSlaveConnectionMinimumIdleSize(multipleServerConfigurations.getSlaveConnectionMinimumIdleSize());
        sentinelServersConfig.setSlaveConnectionPoolSize(multipleServerConfigurations.getSlaveConnectionPoolSize());
        sentinelServersConfig.setFailedSlaveReconnectionInterval(multipleServerConfigurations.getFailedSlaveReconnectionInterval());
        sentinelServersConfig.setFailedSlaveCheckInterval(multipleServerConfigurations.getFailedSlaveCheckInterval());
        sentinelServersConfig.setMasterConnectionMinimumIdleSize(multipleServerConfigurations.getMasterConnectionMinimumIdleSize());
        sentinelServersConfig.setMasterConnectionPoolSize(multipleServerConfigurations.getMasterConnectionPoolSize());
        sentinelServersConfig.setReadMode(multipleServerConfigurations.getReadMode());
        sentinelServersConfig.setSubscriptionMode(multipleServerConfigurations.getSubscriptionMode());
        sentinelServersConfig.setSubscriptionConnectionMinimumIdleSize(multipleServerConfigurations.getSubscriptionConnectionMinimumIdleSize());
        sentinelServersConfig.setSubscriptionConnectionPoolSize(multipleServerConfigurations.getSubscriptionConnectionPoolSize());
        sentinelServersConfig.setDnsMonitoringInterval(multipleServerConfigurations.getDnsMonitoringInterval());
        try {
            sentinelServersConfig.setLoadBalancer((LoadBalancer) Class.forName(multipleServerConfigurations.getLoadBalancer()).newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (String nodeAddress : multipleServerConfigurations.getNodeAddresses()) {
            sentinelServersConfig.addSentinelAddress(prefixAddress(nodeAddress));
        }
        sentinelServersConfig.setPingTimeout(redissonConfigurations.getPingTimeout());
        sentinelServersConfig.setClientName(redissonConfigurations.getClientName());
        sentinelServersConfig.setConnectTimeout(redissonConfigurations.getConnectTimeout());
        sentinelServersConfig.setIdleConnectionTimeout(redissonConfigurations.getIdleConnectionTimeout());
        sentinelServersConfig.setKeepAlive(redissonConfigurations.getKeepAlive());
        sentinelServersConfig.setPassword(redissonConfigurations.getPassword());
        sentinelServersConfig.setPingConnectionInterval(redissonConfigurations.getPingConnectionInterval());
        sentinelServersConfig.setRetryAttempts(redissonConfigurations.getRetryAttempts());
        sentinelServersConfig.setRetryInterval(redissonConfigurations.getRetryInterval());
        sentinelServersConfig.setSslEnableEndpointIdentification(redissonConfigurations.getSslEnableEndpointIdentification());
        sentinelServersConfig.setSslKeystore(redissonConfigurations.getSslKeystore());
        sentinelServersConfig.setSslKeystorePassword(redissonConfigurations.getSslKeystorePassword());
        sentinelServersConfig.setSslProvider(redissonConfigurations.getSslProvider());
        sentinelServersConfig.setSslTruststore(redissonConfigurations.getSslTruststore());
        sentinelServersConfig.setSslTruststorePassword(redissonConfigurations.getSslTruststorePassword());
        sentinelServersConfig.setSubscriptionsPerConnection(redissonConfigurations.getSubscriptionsPerConnection());
        sentinelServersConfig.setTcpNoDelay(redissonConfigurations.getTcpNoDelay());
        sentinelServersConfig.setTimeout(redissonConfigurations.getTimeout());
        return config;
    }
}
