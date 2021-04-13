package com.fermin.redis.lock.core;

import com.fermin.redis.lock.core.impl.FairLock;
import com.fermin.redis.lock.core.impl.ReadLock;
import com.fermin.redis.lock.core.impl.ReentrantLock;
import com.fermin.redis.lock.core.impl.WriteLock;
import com.fermin.redis.lock.model.LockInfo;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class LockFactory {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedissonClient redissonClient;

    public Lock getLock(LockInfo lockInfo) {
        switch (lockInfo.getType()) {
            case REENTRANT:
                return new ReentrantLock(redissonClient, lockInfo);
            case FAIR:
                return new FairLock(redissonClient, lockInfo);
            case READ:
                return new ReadLock(redissonClient, lockInfo);
            case WRITE:
                return new WriteLock(redissonClient, lockInfo);
            default:
                return new ReentrantLock(redissonClient, lockInfo);
        }
    }

}
