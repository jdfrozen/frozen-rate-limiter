package com.frozen.frozenratelimiter;

import com.frozen.frozenratelimiter.conf.Token;
import com.frozen.frozenratelimiter.service.DefaultRateLimiter;
import com.frozen.frozenratelimiter.service.WatiRateLimiter;
import com.frozen.frozenratelimiter.utils.RateLimiterUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: ywwl
 * @Date: 2018/7/27 17:25
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RateLimiterTest {

    private final Object lock = new Object();
    @Test
    public void rateLimiterTest() {
        Token token = new Token(1000);
        RateLimiterUtil rateLimiterUtil=new RateLimiterUtil(10,new WatiRateLimiter(),1000,TimeUnit.MILLISECONDS);
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExecutorService exxc = Executors.newFixedThreadPool(100);
        final CountDownLatch doneSignal = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            Runnable run = new LimitTest(i,rateLimiterUtil,lock,doneSignal) ;
            exxc.execute(run);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            //计数器大于0 时，await()方法会阻塞程序继续执行
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class LimitTest implements Runnable{
        int order;
        RateLimiterUtil rateLimiterUtil;
        CountDownLatch doneSignal;
        LimitTest(int order,RateLimiterUtil rateLimiterUtil,Object lock,CountDownLatch doneSignal){
            this.order=order;
            this.rateLimiterUtil=rateLimiterUtil;
            this.doneSignal=doneSignal;
        }
        @Override
        public void run() {
            System.out.println("第"+order+"线程结果:"+rateLimiterUtil.acquire(1000,TimeUnit.MILLISECONDS));
            doneSignal.countDown();//每调用一次countDown()方法，计数器减1
        }
    }
}
