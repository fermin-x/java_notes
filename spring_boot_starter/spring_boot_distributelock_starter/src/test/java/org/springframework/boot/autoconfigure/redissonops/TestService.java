package org.springframework.boot.autoconfigure.redissonops;


import com.fermin.redis.lock.annotation.GlobalLock;
import com.fermin.redis.lock.annotation.LockKey;
import com.fermin.redis.lock.handler.LockTimeoutStrategy;
import org.springframework.stereotype.Service;


@Service
public class TestService {

    @GlobalLock(waitTime = 10, leaseTime = 60, keys = {"#param"}, lockTimeoutStrategy = LockTimeoutStrategy.FAIL_FAST)
    public String getValue(String param) throws Exception {
        //  if ("sleep".equals(param)) {//线程休眠或者断点阻塞，达到一直占用锁的测试效果
        Thread.sleep(1000 * 3);
        //}
        return "success";
    }

    @GlobalLock(keys = {"#userId"})
    public String getValue(String userId, @LockKey Integer id) throws Exception {
        Thread.sleep(60 * 1000);
        return "success";
    }

    @GlobalLock(keys = {"#user.name", "#user.id"})
    public String getValue(User user) throws Exception {
        Thread.sleep(60 * 1000);
        return "success";
    }

}
