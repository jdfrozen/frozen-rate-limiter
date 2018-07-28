package com.frozen.frozenratelimiter.utils;

import com.frozen.frozenratelimiter.conf.SupplementToken;
import com.frozen.frozenratelimiter.conf.Token;
import com.frozen.frozenratelimiter.service.DefaultRateLimiter;
import com.frozen.frozenratelimiter.service.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @Auther: ywwl
 * @Date: 2018/7/27 16:24
 * @Description:
 */
public class RateLimiterUtil {
    // 定时线程
    private final ScheduledThreadPoolExecutor scheduledCheck = new ScheduledThreadPoolExecutor(2);
    private  Token token;
    private final Object lock=new Object();
    private RateLimiter rateLimiter=new DefaultRateLimiter();

    public RateLimiterUtil(int originToken,RateLimiter rateLimiter,long period,TimeUnit unit){
        this.token=new Token(originToken);
        if (rateLimiter != null) {
            this.rateLimiter = rateLimiter;
        }
        //启动定时补充令牌
        scheduledCheck.scheduleAtFixedRate(new SupplementToken(lock, token), 1, period, unit);
    }


    /**
     *
     * @param timeout
     * @param unit
     * @return
     */
    public boolean acquire(long timeout, TimeUnit unit){
        return rateLimiter.acquire( lock, timeout,  unit, token);
    }

    public void setToken(int originToken){
        token.setToken(originToken);
    }
}
