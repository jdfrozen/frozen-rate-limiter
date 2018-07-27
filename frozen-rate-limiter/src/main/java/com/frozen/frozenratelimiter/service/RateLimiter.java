package com.frozen.frozenratelimiter.service;

import com.frozen.frozenratelimiter.conf.Token;

/**
 * @Auther: ywwl
 * @Date: 2018/7/27 15:48
 * @Description:
 */
public interface RateLimiter {
    public boolean acquire();
}

