package com.fermin.redis.lock.handler;


public class KlockTimeoutException extends RuntimeException {

    public KlockTimeoutException() {
    }

    public KlockTimeoutException(String message) {
        super(message);
    }

    public KlockTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }
}
