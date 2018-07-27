package com.frozen.frozenratelimiter.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: ywwl
 * @Date: 2018/7/26 18:03
 * @Description:
 */
@RestController
@RequestMapping(path = "/rate/limiter")
public class RateLimitercontroller {
    @RequestMapping("/frozen")
    @ResponseBody
    public String createWrongOrder(){
        return "frozenLimiter";
    }
}
