package com.fermin.redis.configuration.strategy;


import com.fermin.redis.configuration.RedissonConfigurations;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.connection.balancer.LoadBalancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 地址格式：
 *      cluster方式至少6个节点(3主3从，3主做sharding，3从用来保证主宕机后可以高可用)
 *      格式为: 127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381,127.0.0.1:6382,127.0.0.1:6383,127.0.0.1:6384
 */
public class ClusterRedissonConfigStrategyImpl extends RedissonConfigStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterRedissonConfigStrategyImpl.class);

    public ClusterRedissonConfigStrategyImpl(RedissonConfigurations redissonConfigurations) {
        this.redissonConfigurations = redissonConfigurations;
    }

    @Override
    public Config createRedissonConfig(Config config, RedissonConfigurations redissonConfigurations) {
        RedissonConfigurations.MultipleServerConfigurations multipleServerConfigurations = redissonConfigurations.getMultipleServerConfig();
        ClusterServersConfig clusterServersConfig = config.useClusterServers();
        clusterServersConfig.setScanInterval(multipleServerConfigurations.getScanInterval());
        clusterServersConfig.setSlaveConnectionMinimumIdleSize(multipleServerConfigurations.getSlaveConnectionMinimumIdleSize());
        clusterServersConfig.setSlaveConnectionPoolSize(multipleServerConfigurations.getSlaveConnectionPoolSize());
        clusterServersConfig.setFailedSlaveReconnectionInterval(multipleServerConfigurations.getFailedSlaveReconnectionInterval());
        clusterServersConfig.setFailedSlaveCheckInterval(multipleServerConfigurations.getFailedSlaveCheckInterval());
        clusterServersConfig.setMasterConnectionMinimumIdleSize(multipleServerConfigurations.getMasterConnectionMinimumIdleSize());
        clusterServersConfig.setMasterConnectionPoolSize(multipleServerConfigurations.getMasterConnectionPoolSize());
        clusterServersConfig.setReadMode(multipleServerConfigurations.getReadMode());
        clusterServersConfig.setSubscriptionMode(multipleServerConfigurations.getSubscriptionMode());
        clusterServersConfig.setSubscriptionConnectionMinimumIdleSize(multipleServerConfigurations.getSubscriptionConnectionMinimumIdleSize());
        clusterServersConfig.setSubscriptionConnectionPoolSize(multipleServerConfigurations.getSubscriptionConnectionPoolSize());
        clusterServersConfig.setDnsMonitoringInterval(multipleServerConfigurations.getDnsMonitoringInterval());
        try {
            clusterServersConfig.setLoadBalancer((LoadBalancer) Class.forName(multipleServerConfigurations.getLoadBalancer()).newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (String nodeAddress : multipleServerConfigurations.getNodeAddresses()) {
            clusterServersConfig.addNodeAddress(prefixAddress(nodeAddress));
        }
        clusterServersConfig.setPingTimeout(redissonConfigurations.getPingTimeout());
        clusterServersConfig.setClientName(redissonConfigurations.getClientName());
        clusterServersConfig.setConnectTimeout(redissonConfigurations.getConnectTimeout());
        clusterServersConfig.setIdleConnectionTimeout(redissonConfigurations.getIdleConnectionTimeout());
        clusterServersConfig.setKeepAlive(redissonConfigurations.getKeepAlive());
        clusterServersConfig.setPassword(redissonConfigurations.getPassword());
        clusterServersConfig.setPingConnectionInterval(redissonConfigurations.getPingConnectionInterval());
        clusterServersConfig.setRetryAttempts(redissonConfigurations.getRetryAttempts());
        clusterServersConfig.setRetryInterval(redissonConfigurations.getRetryInterval());
        clusterServersConfig.setSslEnableEndpointIdentification(redissonConfigurations.getSslEnableEndpointIdentification());
        clusterServersConfig.setSslKeystore(redissonConfigurations.getSslKeystore());
        clusterServersConfig.setSslKeystorePassword(redissonConfigurations.getSslKeystorePassword());
        clusterServersConfig.setSslProvider(redissonConfigurations.getSslProvider());
        clusterServersConfig.setSslTruststore(redissonConfigurations.getSslTruststore());
        clusterServersConfig.setSslTruststorePassword(redissonConfigurations.getSslTruststorePassword());
        clusterServersConfig.setSubscriptionsPerConnection(redissonConfigurations.getSubscriptionsPerConnection());
        clusterServersConfig.setTcpNoDelay(redissonConfigurations.getTcpNoDelay());
        clusterServersConfig.setTimeout(redissonConfigurations.getTimeout());

        return config;
    }
}
