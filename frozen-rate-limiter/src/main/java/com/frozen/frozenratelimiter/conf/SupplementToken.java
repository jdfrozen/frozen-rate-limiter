package com.frozen.frozenratelimiter.conf;

/**
 * @Auther: ywwl
 * @Date: 2018/7/28 18:23
 * @Description:
 */
public class SupplementToken implements Runnable{
    Object lock;
    Token token;
    public  SupplementToken(Object lock,Token token){
        this.lock=lock;
        this.token=token;
    }
    @Override
    public void run() {
        token.supplementToken();
        synchronized (lock) {
            lock.notifyAll();
            //System.out.println("end notify() ThreadName=" + Thread.currentThread().getName());
        }
    }
}
