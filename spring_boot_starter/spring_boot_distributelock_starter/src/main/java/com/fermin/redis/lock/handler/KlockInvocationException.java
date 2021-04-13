package com.fermin.redis.lock.handler;


public class KlockInvocationException extends RuntimeException {

    public KlockInvocationException() {
    }

    public KlockInvocationException(String message) {
        super(message);
    }

    public KlockInvocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
