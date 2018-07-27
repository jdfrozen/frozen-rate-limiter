package com.frozen.frozenratelimiter.service;

import com.frozen.frozenratelimiter.conf.Token;
import com.frozen.frozenratelimiter.service.RateLimiter;
import org.springframework.stereotype.Service;

/**
 * @Auther: ywwl
 * @Date: 2018/7/27 15:49
 * @Description:
 */
@Service
public class DefaultRateLimiter implements RateLimiter {
    private final Object lock = new Object();
    @Override
    public boolean acquire() {
        int token= Token.getToken();
        if(token>0){
            System.out.println("当前token:"+String.valueOf(Token.consumeToken()));
            return true;
        }else{
            return false;
        }
    }
}
