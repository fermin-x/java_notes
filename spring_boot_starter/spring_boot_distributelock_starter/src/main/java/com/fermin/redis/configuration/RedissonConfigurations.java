package com.fermin.redis.configuration;

import com.fermin.redis.enums.Model;
import org.redisson.config.ReadMode;
import org.redisson.config.SslProvider;
import org.redisson.config.SubscriptionMode;
import org.redisson.config.TransportMode;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = RedissonConfigurations.PREFIX)
public class RedissonConfigurations {

    public static final String PREFIX = "redisson";
    public static final String REDISSON_LOCK_PREFIX = PREFIX + ".lock";

    private Model model = Model.SINGLE;

    private String codec = "org.redisson.codec.JsonJacksonCodec";
    private Integer threads;
    private Integer nettyThreads;
    private TransportMode transportMode = TransportMode.NIO;

    //公共参数
    private Integer idleConnectionTimeout = 10000;
    private Integer pingTimeout = 1000;
    private Integer connectTimeout = 10000;
    private Integer timeout = 3000;
    private Integer retryAttempts = 3;
    private Integer retryInterval = 1500;
    private String password;
    private Integer subscriptionsPerConnection = 5;
    private String clientName;
    private Boolean sslEnableEndpointIdentification = true;
    private SslProvider sslProvider = SslProvider.JDK;
    private URI sslTruststore;
    private String sslTruststorePassword;
    private URI sslKeystore;
    private String sslKeystorePassword;
    private Integer pingConnectionInterval = 0;
    private Boolean keepAlive = false;
    private Boolean tcpNoDelay = false;
    private Boolean referenceEnabled = true;
    private Long lockWatchdogTimeout = 30000L;
    private Boolean keepPubSubOrder = true;
    private Boolean decodeInExecutor = false;
    private Boolean useScriptCache = false;
    private Integer minCleanUpDelay = 5;
    private Integer maxCleanUpDelay = 1800;

    //等待加锁超时时间 -1一直等待
    private Long attemptTimeout = 10000L;

    //数据缓存时间 默认30分钟
    private Long dataValidTime = 1000 * 60 * 30L;

    private SingleServerConfigurations singleServerConfigurations;
    //    @NestedConfigurationProperty
    private MultipleServerConfigurations multipleServerConfigurations;

    public Long getDataValidTime() {
        return dataValidTime;
    }

    public void setDataValidTime(Long dataValidTime) {
        this.dataValidTime = dataValidTime;
    }

    public Long getAttemptTimeout() {
        return attemptTimeout;
    }

    public void setAttemptTimeout(Long attemptTimeout) {
        this.attemptTimeout = attemptTimeout;
    }

    public Boolean getReferenceEnabled() {
        return referenceEnabled;
    }

    public void setReferenceEnabled(Boolean referenceEnabled) {
        this.referenceEnabled = referenceEnabled;
    }

    public Long getLockWatchdogTimeout() {
        return lockWatchdogTimeout;
    }

    public void setLockWatchdogTimeout(Long lockWatchdogTimeout) {
        this.lockWatchdogTimeout = lockWatchdogTimeout;
    }

    public Boolean getKeepPubSubOrder() {
        return keepPubSubOrder;
    }

    public void setKeepPubSubOrder(Boolean keepPubSubOrder) {
        this.keepPubSubOrder = keepPubSubOrder;
    }

    public Boolean getDecodeInExecutor() {
        return decodeInExecutor;
    }

    public void setDecodeInExecutor(Boolean decodeInExecutor) {
        this.decodeInExecutor = decodeInExecutor;
    }

    public Boolean getUseScriptCache() {
        return useScriptCache;
    }

    public void setUseScriptCache(Boolean useScriptCache) {
        this.useScriptCache = useScriptCache;
    }

    public Integer getMinCleanUpDelay() {
        return minCleanUpDelay;
    }

    public void setMinCleanUpDelay(Integer minCleanUpDelay) {
        this.minCleanUpDelay = minCleanUpDelay;
    }

    public Integer getMaxCleanUpDelay() {
        return maxCleanUpDelay;
    }

    public void setMaxCleanUpDelay(Integer maxCleanUpDelay) {
        this.maxCleanUpDelay = maxCleanUpDelay;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public SingleServerConfigurations getSingleServerConfig() {
        return singleServerConfigurations;
    }

    public void setSingleServerConfig(SingleServerConfigurations singleServerConfigurations) {
        this.singleServerConfigurations = singleServerConfigurations;
    }

    public MultipleServerConfigurations getMultipleServerConfig() {
        return multipleServerConfigurations;
    }

    public void setMultipleServerConfig(MultipleServerConfigurations multipleServerConfigurations) {
        this.multipleServerConfigurations = multipleServerConfigurations;
    }

    public Boolean getSslEnableEndpointIdentification() {
        return sslEnableEndpointIdentification;
    }

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public Boolean getTcpNoDelay() {
        return tcpNoDelay;
    }

    public Integer getThreads() {
        return threads;
    }

    public void setThreads(Integer threads) {
        this.threads = threads;
    }

    public Integer getNettyThreads() {
        return nettyThreads;
    }

    public void setNettyThreads(Integer nettyThreads) {
        this.nettyThreads = nettyThreads;
    }

    public TransportMode getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(TransportMode transportMode) {
        this.transportMode = transportMode;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public int getIdleConnectionTimeout() {
        return idleConnectionTimeout;
    }

    public void setIdleConnectionTimeout(Integer idleConnectionTimeout) {
        this.idleConnectionTimeout = idleConnectionTimeout;
    }

    public void setIdleConnectionTimeout(int idleConnectionTimeout) {
        this.idleConnectionTimeout = idleConnectionTimeout;
    }

    public int getPingTimeout() {
        return pingTimeout;
    }

    public void setPingTimeout(Integer pingTimeout) {
        this.pingTimeout = pingTimeout;
    }

    public void setPingTimeout(int pingTimeout) {
        this.pingTimeout = pingTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getRetryAttempts() {
        return retryAttempts;
    }

    public void setRetryAttempts(Integer retryAttempts) {
        this.retryAttempts = retryAttempts;
    }

    public void setRetryAttempts(int retryAttempts) {
        this.retryAttempts = retryAttempts;
    }

    public int getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(Integer retryInterval) {
        this.retryInterval = retryInterval;
    }

    public void setRetryInterval(int retryInterval) {
        this.retryInterval = retryInterval;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSubscriptionsPerConnection() {
        return subscriptionsPerConnection;
    }

    public void setSubscriptionsPerConnection(Integer subscriptionsPerConnection) {
        this.subscriptionsPerConnection = subscriptionsPerConnection;
    }

    public void setSubscriptionsPerConnection(int subscriptionsPerConnection) {
        this.subscriptionsPerConnection = subscriptionsPerConnection;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public boolean isSslEnableEndpointIdentification() {
        return sslEnableEndpointIdentification;
    }

    public void setSslEnableEndpointIdentification(Boolean sslEnableEndpointIdentification) {
        this.sslEnableEndpointIdentification = sslEnableEndpointIdentification;
    }

    public void setSslEnableEndpointIdentification(boolean sslEnableEndpointIdentification) {
        this.sslEnableEndpointIdentification = sslEnableEndpointIdentification;
    }

    public SslProvider getSslProvider() {
        return sslProvider;
    }

    public void setSslProvider(SslProvider sslProvider) {
        this.sslProvider = sslProvider;
    }

    public URI getSslTruststore() {
        return sslTruststore;
    }

    public void setSslTruststore(URI sslTruststore) {
        this.sslTruststore = sslTruststore;
    }

    public String getSslTruststorePassword() {
        return sslTruststorePassword;
    }

    public void setSslTruststorePassword(String sslTruststorePassword) {
        this.sslTruststorePassword = sslTruststorePassword;
    }

    public URI getSslKeystore() {
        return sslKeystore;
    }

    public void setSslKeystore(URI sslKeystore) {
        this.sslKeystore = sslKeystore;
    }

    public String getSslKeystorePassword() {
        return sslKeystorePassword;
    }

    public void setSslKeystorePassword(String sslKeystorePassword) {
        this.sslKeystorePassword = sslKeystorePassword;
    }

    public int getPingConnectionInterval() {
        return pingConnectionInterval;
    }

    public void setPingConnectionInterval(Integer pingConnectionInterval) {
        this.pingConnectionInterval = pingConnectionInterval;
    }

    public void setPingConnectionInterval(int pingConnectionInterval) {
        this.pingConnectionInterval = pingConnectionInterval;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public boolean isTcpNoDelay() {
        return tcpNoDelay;
    }

    public void setTcpNoDelay(Boolean tcpNoDelay) {
        this.tcpNoDelay = tcpNoDelay;
    }

    public void setTcpNoDelay(boolean tcpNoDelay) {
        this.tcpNoDelay = tcpNoDelay;
    }

    public static class MultipleServerConfigurations {

        private String loadBalancer = "org.redisson.connection.balancer.RoundRobinLoadBalancer";
        private Integer slaveConnectionMinimumIdleSize = 32;
        private Integer slaveConnectionPoolSize = 64;
        private Integer failedSlaveReconnectionInterval = 3000;
        private Integer failedSlaveCheckInterval = 180000;
        private Integer masterConnectionMinimumIdleSize = 32;
        private Integer masterConnectionPoolSize = 64;
        private ReadMode readMode = ReadMode.SLAVE;
        private SubscriptionMode subscriptionMode = SubscriptionMode.SLAVE;
        private Integer subscriptionConnectionMinimumIdleSize = 1;
        private Integer subscriptionConnectionPoolSize = 50;
        private Long dnsMonitoringInterval = 5000L;

        private List<String> nodeAddresses = new ArrayList();

        //集群,哨兵,云托管
        private Integer scanInterval = 1000;

        //哨兵模式,云托管,主从
        private Integer database = 0;

        //哨兵模式
        private String masterName;

        public String getMasterName() {
            return masterName;
        }

        public void setMasterName(String masterName) {
            this.masterName = masterName;
        }

        public String getLoadBalancer() {
            return loadBalancer;
        }

        public void setLoadBalancer(String loadBalancer) {
            this.loadBalancer = loadBalancer;
        }

        public Integer getSlaveConnectionMinimumIdleSize() {
            return slaveConnectionMinimumIdleSize;
        }

        public void setSlaveConnectionMinimumIdleSize(Integer slaveConnectionMinimumIdleSize) {
            this.slaveConnectionMinimumIdleSize = slaveConnectionMinimumIdleSize;
        }

        public Integer getSlaveConnectionPoolSize() {
            return slaveConnectionPoolSize;
        }

        public void setSlaveConnectionPoolSize(Integer slaveConnectionPoolSize) {
            this.slaveConnectionPoolSize = slaveConnectionPoolSize;
        }

        public Integer getFailedSlaveReconnectionInterval() {
            return failedSlaveReconnectionInterval;
        }

        public void setFailedSlaveReconnectionInterval(Integer failedSlaveReconnectionInterval) {
            this.failedSlaveReconnectionInterval = failedSlaveReconnectionInterval;
        }

        public Integer getFailedSlaveCheckInterval() {
            return failedSlaveCheckInterval;
        }

        public void setFailedSlaveCheckInterval(Integer failedSlaveCheckInterval) {
            this.failedSlaveCheckInterval = failedSlaveCheckInterval;
        }

        public Integer getMasterConnectionMinimumIdleSize() {
            return masterConnectionMinimumIdleSize;
        }

        public void setMasterConnectionMinimumIdleSize(Integer masterConnectionMinimumIdleSize) {
            this.masterConnectionMinimumIdleSize = masterConnectionMinimumIdleSize;
        }

        public Integer getMasterConnectionPoolSize() {
            return masterConnectionPoolSize;
        }

        public void setMasterConnectionPoolSize(Integer masterConnectionPoolSize) {
            this.masterConnectionPoolSize = masterConnectionPoolSize;
        }

        public ReadMode getReadMode() {
            return readMode;
        }

        public void setReadMode(ReadMode readMode) {
            this.readMode = readMode;
        }

        public SubscriptionMode getSubscriptionMode() {
            return subscriptionMode;
        }

        public void setSubscriptionMode(SubscriptionMode subscriptionMode) {
            this.subscriptionMode = subscriptionMode;
        }

        public Integer getSubscriptionConnectionMinimumIdleSize() {
            return subscriptionConnectionMinimumIdleSize;
        }

        public void setSubscriptionConnectionMinimumIdleSize(Integer subscriptionConnectionMinimumIdleSize) {
            this.subscriptionConnectionMinimumIdleSize = subscriptionConnectionMinimumIdleSize;
        }

        public Integer getSubscriptionConnectionPoolSize() {
            return subscriptionConnectionPoolSize;
        }

        public void setSubscriptionConnectionPoolSize(Integer subscriptionConnectionPoolSize) {
            this.subscriptionConnectionPoolSize = subscriptionConnectionPoolSize;
        }

        public Long getDnsMonitoringInterval() {
            return dnsMonitoringInterval;
        }

        public void setDnsMonitoringInterval(Long dnsMonitoringInterval) {
            this.dnsMonitoringInterval = dnsMonitoringInterval;
        }

        public List<String> getNodeAddresses() {
            return nodeAddresses;
        }

        public void setNodeAddresses(List<String> nodeAddresses) {
            this.nodeAddresses = nodeAddresses;
        }

        public Integer getScanInterval() {
            return scanInterval;
        }

        public void setScanInterval(Integer scanInterval) {
            this.scanInterval = scanInterval;
        }

        public Integer getDatabase() {
            return database;
        }

        public void setDatabase(Integer database) {
            this.database = database;
        }
    }

    public static class SingleServerConfigurations {

        private String address;
        private Integer subscriptionConnectionMinimumIdleSize = 1;
        private Integer subscriptionConnectionPoolSize = 50;
        private Integer connectionMinimumIdleSize = 32;
        private Integer connectionPoolSize = 64;
        private Integer database = 0;
        private Long dnsMonitoringInterval = 5000L;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getSubscriptionConnectionMinimumIdleSize() {
            return subscriptionConnectionMinimumIdleSize;
        }

        public void setSubscriptionConnectionMinimumIdleSize(Integer subscriptionConnectionMinimumIdleSize) {
            this.subscriptionConnectionMinimumIdleSize = subscriptionConnectionMinimumIdleSize;
        }

        public Integer getSubscriptionConnectionPoolSize() {
            return subscriptionConnectionPoolSize;
        }

        public void setSubscriptionConnectionPoolSize(Integer subscriptionConnectionPoolSize) {
            this.subscriptionConnectionPoolSize = subscriptionConnectionPoolSize;
        }

        public Integer getConnectionMinimumIdleSize() {
            return connectionMinimumIdleSize;
        }

        public void setConnectionMinimumIdleSize(Integer connectionMinimumIdleSize) {
            this.connectionMinimumIdleSize = connectionMinimumIdleSize;
        }

        public Integer getConnectionPoolSize() {
            return connectionPoolSize;
        }

        public void setConnectionPoolSize(Integer connectionPoolSize) {
            this.connectionPoolSize = connectionPoolSize;
        }

        public Integer getDatabase() {
            return database;
        }

        public void setDatabase(Integer database) {
            this.database = database;
        }

        public Long getDnsMonitoringInterval() {
            return dnsMonitoringInterval;
        }

        public void setDnsMonitoringInterval(Long dnsMonitoringInterval) {
            this.dnsMonitoringInterval = dnsMonitoringInterval;
        }
    }

}
