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
    private volatile  AtomicInteger token;
    private int supToken;
    public  Token(int originToken){
        token=new AtomicInteger(originToken);
        supToken =originToken;
    }
    public void setToken(int originToken){
        supToken =originToken;
        token.set(supToken);
    }
    public int getToken(){
     return token.get();
    }
    public void supplementToken(){
        token.set(supToken);
    }
    public  int consumeToken(){
        return token.decrementAndGet(); }
}
