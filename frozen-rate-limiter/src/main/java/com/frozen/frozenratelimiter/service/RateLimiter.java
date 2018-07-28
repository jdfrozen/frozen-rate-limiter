package com.frozen.frozenratelimiter.service;

import com.frozen.frozenratelimiter.conf.Token;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: ywwl
 * @Date: 2018/7/27 15:48
 * @Description:
 */
public interface RateLimiter {
    public boolean acquire(Object lock,long timeout, TimeUnit unit,Token token);
}

