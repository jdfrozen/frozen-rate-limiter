package com.frozen.frozenratelimiter.conf;

import jdk.internal.dynalink.beans.StaticClass;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: ywwl
 * @Date: 2018/7/27 15:20
 * @Description:
 */
public class Token {
    private volatile static AtomicInteger token;
    private static int supToken;
    public static void originToken(int originToken){
        token=new AtomicInteger(originToken);
        supToken =originToken;
    }
    public static int getToken(){
     return token.get();
    }

    public static void supplementToken(){
         token.set(supToken);
    }
    public static int consumeToken(){
        return token.decrementAndGet();
    }
}
