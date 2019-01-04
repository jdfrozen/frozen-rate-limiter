package com.frozen.xxljobboot.controller;

import com.frozen.xxljobboot.controller.annotation.PermessionLimit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @RequestMapping("/healthCheck")
    @PermessionLimit(limit=false)
    public String healthCheck(){
        return "success";
    }

    @RequestMapping("/info")
    @PermessionLimit(limit=false)
    public String info(){return "success";}
}
