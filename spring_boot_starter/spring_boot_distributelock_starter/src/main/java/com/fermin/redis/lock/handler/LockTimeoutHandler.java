package com.fermin.redis.lock.handler;

import com.fermin.redis.lock.core.Lock;
import com.fermin.redis.lock.model.LockInfo;
import org.aspectj.lang.JoinPoint;

public interface LockTimeoutHandler {

    void handle(LockInfo lockInfo, Lock lock, JoinPoint joinPoint);
}
