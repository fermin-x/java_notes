package com.fermin.redis.lock.core;


public interface Lock {

    boolean acquire();

    boolean release();
}

