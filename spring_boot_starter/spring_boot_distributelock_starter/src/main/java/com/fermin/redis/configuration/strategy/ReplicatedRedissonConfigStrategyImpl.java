package com.fermin.redis.configuration.strategy;

import com.fermin.redis.configuration.RedissonConfigurations;
import org.redisson.config.Config;
import org.redisson.config.ReplicatedServersConfig;
import org.redisson.connection.balancer.LoadBalancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReplicatedRedissonConfigStrategyImpl extends RedissonConfigStrategy {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    public ReplicatedRedissonConfigStrategyImpl(RedissonConfigurations redissonConfigurations) {
        this.redissonConfigurations = redissonConfigurations;
    }

    @Override
    public Config createRedissonConfig(Config config, RedissonConfigurations redissonConfigurations) {
        RedissonConfigurations.MultipleServerConfigurations multipleServerConfigurations = redissonConfigurations.getMultipleServerConfig();
        ReplicatedServersConfig replicatedServersConfig = config.useReplicatedServers();
        replicatedServersConfig.setDatabase(multipleServerConfigurations.getDatabase());
        replicatedServersConfig.setScanInterval(multipleServerConfigurations.getScanInterval());
        replicatedServersConfig.setSlaveConnectionMinimumIdleSize(multipleServerConfigurations.getSlaveConnectionMinimumIdleSize());
        replicatedServersConfig.setSlaveConnectionPoolSize(multipleServerConfigurations.getSlaveConnectionPoolSize());
        replicatedServersConfig.setFailedSlaveReconnectionInterval(multipleServerConfigurations.getFailedSlaveReconnectionInterval());
        replicatedServersConfig.setFailedSlaveCheckInterval(multipleServerConfigurations.getFailedSlaveCheckInterval());
        replicatedServersConfig.setMasterConnectionMinimumIdleSize(multipleServerConfigurations.getMasterConnectionMinimumIdleSize());
        replicatedServersConfig.setMasterConnectionPoolSize(multipleServerConfigurations.getMasterConnectionPoolSize());
        replicatedServersConfig.setReadMode(multipleServerConfigurations.getReadMode());
        replicatedServersConfig.setSubscriptionMode(multipleServerConfigurations.getSubscriptionMode());
        replicatedServersConfig.setSubscriptionConnectionMinimumIdleSize(multipleServerConfigurations.getSubscriptionConnectionMinimumIdleSize());
        replicatedServersConfig.setSubscriptionConnectionPoolSize(multipleServerConfigurations.getSubscriptionConnectionPoolSize());
        replicatedServersConfig.setDnsMonitoringInterval(multipleServerConfigurations.getDnsMonitoringInterval());
        try {
            replicatedServersConfig.setLoadBalancer((LoadBalancer) Class.forName(multipleServerConfigurations.getLoadBalancer()).newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (String nodeAddress : multipleServerConfigurations.getNodeAddresses()) {
            replicatedServersConfig.addNodeAddress(prefixAddress(nodeAddress));
        }
        replicatedServersConfig.setPingTimeout(redissonConfigurations.getPingTimeout());
        replicatedServersConfig.setClientName(redissonConfigurations.getClientName());
        replicatedServersConfig.setConnectTimeout(redissonConfigurations.getConnectTimeout());
        replicatedServersConfig.setIdleConnectionTimeout(redissonConfigurations.getIdleConnectionTimeout());
        replicatedServersConfig.setKeepAlive(redissonConfigurations.getKeepAlive());
        replicatedServersConfig.setPassword(redissonConfigurations.getPassword());
        replicatedServersConfig.setPingConnectionInterval(redissonConfigurations.getPingConnectionInterval());
        replicatedServersConfig.setRetryAttempts(redissonConfigurations.getRetryAttempts());
        replicatedServersConfig.setRetryInterval(redissonConfigurations.getRetryInterval());
        replicatedServersConfig.setSslEnableEndpointIdentification(redissonConfigurations.getSslEnableEndpointIdentification());
        replicatedServersConfig.setSslKeystore(redissonConfigurations.getSslKeystore());
        replicatedServersConfig.setSslKeystorePassword(redissonConfigurations.getSslKeystorePassword());
        replicatedServersConfig.setSslProvider(redissonConfigurations.getSslProvider());
        replicatedServersConfig.setSslTruststore(redissonConfigurations.getSslTruststore());
        replicatedServersConfig.setSslTruststorePassword(redissonConfigurations.getSslTruststorePassword());
        replicatedServersConfig.setSubscriptionsPerConnection(redissonConfigurations.getSubscriptionsPerConnection());
        replicatedServersConfig.setTcpNoDelay(redissonConfigurations.getTcpNoDelay());
        replicatedServersConfig.setTimeout(redissonConfigurations.getTimeout());
        return config;
    }
}
