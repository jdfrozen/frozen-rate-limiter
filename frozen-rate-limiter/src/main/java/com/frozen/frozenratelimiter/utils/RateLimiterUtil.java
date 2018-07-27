package com.frozen.frozenratelimiter.utils;

import com.frozen.frozenratelimiter.conf.Token;
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
@Service
public class RateLimiterUtil {
    // 定时线程
    private final ScheduledThreadPoolExecutor scheduledCheck = new ScheduledThreadPoolExecutor(2);
    //初始化的时候启动定时任务
    public void init(){
        Token.originToken(1);
        System.out.println("令牌桶初始话成功");
        scheduledCheck.scheduleAtFixedRate(new SupplementToken(), 1, 100, TimeUnit.MILLISECONDS);
    }
        private  class SupplementToken implements Runnable{
        @Override
        public void run() {Token.supplementToken(); }
    }
}
