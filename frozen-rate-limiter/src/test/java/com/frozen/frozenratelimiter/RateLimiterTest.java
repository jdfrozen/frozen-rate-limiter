package com.frozen.frozenratelimiter;

import com.frozen.frozenratelimiter.service.DefaultRateLimiter;
import com.frozen.frozenratelimiter.utils.RateLimiterUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: ywwl
 * @Date: 2018/7/27 17:25
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RateLimiterTest {
    @Autowired
    private DefaultRateLimiter rateLimiter;
    @Autowired
    private RateLimiterUtil rateLimiterUtil;
    @Test
    public void rateLimiterTest() {
        rateLimiterUtil.init();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ExecutorService exxc = Executors.newFixedThreadPool(100);
        final CountDownLatch doneSignal = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            Runnable run = new LimitTest(i,doneSignal,rateLimiter) ;
            exxc.execute(run);
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            //计数器大于0 时，await()方法会阻塞程序继续执行
            doneSignal.await();
            System.out.println("全部执行完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class LimitTest implements Runnable{
        private int order;
        CountDownLatch doneSignal;
        DefaultRateLimiter rateLimiter;
        LimitTest(int order,CountDownLatch doneSignal,DefaultRateLimiter rateLimiter){
            this.order=order;
            this.doneSignal=doneSignal;
            this.rateLimiter=rateLimiter;
        }
        @Override
        public void run() {
            System.out.println("第"+order+"线程结果:"+rateLimiter.acquire());
            doneSignal.countDown();//每调用一次countDown()方法，计数器减1
        }
    }
}
