package com.fermin.redis.lock.aop;

import com.fermin.redis.configuration.RedissonConfigurations;
import com.fermin.redis.lock.annotation.GlobalLock;
import com.fermin.redis.lock.enums.LockModel;
import com.fermin.redis.lock.model.LockInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class LockInfoProvider {

    private static final String LOCK_NAME_PREFIX = "lock";
    private static final String LOCK_NAME_SEPARATOR = ".";
    private static final Logger logger = LoggerFactory.getLogger(LockInfoProvider.class);

    @Autowired
    private RedissonConfigurations klockConfig;

    @Autowired
    private BusinessKeyProvider businessKeyProvider;

    LockInfo get(JoinPoint joinPoint, GlobalLock klock) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        LockModel type = klock.lockType();
        String businessKeyName = businessKeyProvider.getKeyName(joinPoint, klock);
        //锁的名字，锁的粒度就是这里控制的
        String lockName = LOCK_NAME_PREFIX + LOCK_NAME_SEPARATOR + getName(klock.name(), signature) + businessKeyName;
        long waitTime = klock.waitTime();
        long leaseTime = klock.leaseTime();
        //如果占用锁的时间设计不合理，则打印相应的警告提示
        if (leaseTime == -1 && logger.isWarnEnabled()) {
            logger.warn("Trying to acquire Lock({}) with no expiration, " +
                    "Klock will keep prolong the lock expiration while the lock is still holding by current thread. " +
                    "This may cause dead lock in some circumstances.", lockName);
        }
        return new LockInfo(type, lockName, waitTime, leaseTime);
    }

    /**
     * 获取锁的name，如果没有指定，则按全类名拼接方法名处理
     *
     * @param annotationName
     * @param signature
     * @return
     */
    private String getName(String annotationName, MethodSignature signature) {
        if (annotationName.isEmpty()) {
            return String.format("%s.%s", signature.getDeclaringTypeName(), signature.getMethod().getName());
        } else {
            return annotationName;
        }
    }

}
