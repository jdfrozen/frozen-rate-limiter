package com.frozen.frozenratelimiter.service;

import com.frozen.frozenratelimiter.conf.Token;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: ywwl
 * @Date: 2018/7/28 13:50
 * @Description:
 */
public class WatiRateLimiter implements RateLimiter{

    @Override
    public boolean acquire(Object lock,long timeout, TimeUnit unit,Token token) {
        //等待时间
        long timeoutMicros = Math.max(unit.toMillis(timeout), 0L);
        long future = System.currentTimeMillis() + timeoutMicros;
        while (true) {
            if (System.currentTimeMillis() > future) {
                return false;
            } else {
                int tokenLimiter = token.getToken();
                if (tokenLimiter > 0) {
                    System.out.println("当前token:" + String.valueOf(token.consumeToken()));
                    return true;
                } else {
                    synchronized (lock) {
                        try {
                            //System.out.println("wait() ThreadName=" + Thread.currentThread().getName());
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } finally {

                        }
                    }
                }
            }
        }
    }
}
