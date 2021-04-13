package com.fermin.redis.configuration.strategy;


import com.fermin.redis.configuration.RedissonConfigurations;
import org.redisson.config.Config;
import org.redisson.config.MasterSlaveServersConfig;
import org.redisson.connection.balancer.LoadBalancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 主从方式Redisson配置
 * 连接方式：主节点,子节点,子节点
 * 格式为: 127.0.0.1:6379,127.0.0.1:6380,127.0.0.1:6381
 */
public class MasterslaveRedissonConfigStrategyImpl extends RedissonConfigStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterRedissonConfigStrategyImpl.class);

    public MasterslaveRedissonConfigStrategyImpl(RedissonConfigurations redissonConfigurations) {
        this.redissonConfigurations = redissonConfigurations;
    }

    @Override
    public Config createRedissonConfig(Config config, RedissonConfigurations redissonConfigurations) {
        RedissonConfigurations.MultipleServerConfigurations multipleServerConfigurations = redissonConfigurations.getMultipleServerConfig();
        MasterSlaveServersConfig masterSlaveServersConfig = config.useMasterSlaveServers();
        masterSlaveServersConfig.setDatabase(multipleServerConfigurations.getDatabase());
        masterSlaveServersConfig.setSlaveConnectionMinimumIdleSize(multipleServerConfigurations.getSlaveConnectionMinimumIdleSize());
        masterSlaveServersConfig.setSlaveConnectionPoolSize(multipleServerConfigurations.getSlaveConnectionPoolSize());
        masterSlaveServersConfig.setFailedSlaveReconnectionInterval(multipleServerConfigurations.getFailedSlaveReconnectionInterval());
        masterSlaveServersConfig.setFailedSlaveCheckInterval(multipleServerConfigurations.getFailedSlaveCheckInterval());
        masterSlaveServersConfig.setMasterConnectionMinimumIdleSize(multipleServerConfigurations.getMasterConnectionMinimumIdleSize());
        masterSlaveServersConfig.setMasterConnectionPoolSize(multipleServerConfigurations.getMasterConnectionPoolSize());
        masterSlaveServersConfig.setReadMode(multipleServerConfigurations.getReadMode());
        masterSlaveServersConfig.setSubscriptionMode(multipleServerConfigurations.getSubscriptionMode());
        masterSlaveServersConfig.setSubscriptionConnectionMinimumIdleSize(multipleServerConfigurations.getSubscriptionConnectionMinimumIdleSize());
        masterSlaveServersConfig.setSubscriptionConnectionPoolSize(multipleServerConfigurations.getSubscriptionConnectionPoolSize());
        masterSlaveServersConfig.setDnsMonitoringInterval(multipleServerConfigurations.getDnsMonitoringInterval());
        try {
            masterSlaveServersConfig.setLoadBalancer((LoadBalancer) Class.forName(multipleServerConfigurations.getLoadBalancer()).newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int index = 0;
        for (String nodeAddress : multipleServerConfigurations.getNodeAddresses()) {
            if (index++ == 0) {
                masterSlaveServersConfig.setMasterAddress(prefixAddress(nodeAddress));
            } else {
                masterSlaveServersConfig.addSlaveAddress(prefixAddress(nodeAddress));
            }
        }
        masterSlaveServersConfig.setPingTimeout(redissonConfigurations.getPingTimeout());
        masterSlaveServersConfig.setClientName(redissonConfigurations.getClientName());
        masterSlaveServersConfig.setConnectTimeout(redissonConfigurations.getConnectTimeout());
        masterSlaveServersConfig.setIdleConnectionTimeout(redissonConfigurations.getIdleConnectionTimeout());
        masterSlaveServersConfig.setKeepAlive(redissonConfigurations.getKeepAlive());
        masterSlaveServersConfig.setPassword(redissonConfigurations.getPassword());
        masterSlaveServersConfig.setPingConnectionInterval(redissonConfigurations.getPingConnectionInterval());
        masterSlaveServersConfig.setRetryAttempts(redissonConfigurations.getRetryAttempts());
        masterSlaveServersConfig.setRetryInterval(redissonConfigurations.getRetryInterval());
        masterSlaveServersConfig.setSslEnableEndpointIdentification(redissonConfigurations.getSslEnableEndpointIdentification());
        masterSlaveServersConfig.setSslKeystore(redissonConfigurations.getSslKeystore());
        masterSlaveServersConfig.setSslKeystorePassword(redissonConfigurations.getSslKeystorePassword());
        masterSlaveServersConfig.setSslProvider(redissonConfigurations.getSslProvider());
        masterSlaveServersConfig.setSslTruststore(redissonConfigurations.getSslTruststore());
        masterSlaveServersConfig.setSslTruststorePassword(redissonConfigurations.getSslTruststorePassword());
        masterSlaveServersConfig.setSubscriptionsPerConnection(redissonConfigurations.getSubscriptionsPerConnection());
        masterSlaveServersConfig.setTcpNoDelay(redissonConfigurations.getTcpNoDelay());
        masterSlaveServersConfig.setTimeout(redissonConfigurations.getTimeout());
        return config;
    }

}
