package com.frozen.frozenratelimiter.service;

import com.frozen.frozenratelimiter.conf.Token;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: ywwl
 * @Date: 2018/7/27 15:49
 * @Description:
 */
public class DefaultRateLimiter implements RateLimiter {
    @Override
    public boolean acquire(Object lock,long timeout, TimeUnit unit,Token token) {
        int tokenLimiter= token.getToken();
        if(tokenLimiter>0){
            System.out.println("当前token:"+String.valueOf(token.consumeToken()));
            return true;
        }else{
            return false;
        }
    }
}
