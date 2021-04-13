package com.fermin.redis.lock.handler;


import com.fermin.redis.lock.model.LockInfo;

public interface ReleaseTimeoutHandler {

    void handle(LockInfo lockInfo);
}
