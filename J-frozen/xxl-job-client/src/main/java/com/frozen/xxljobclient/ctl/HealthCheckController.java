package com.frozen.xxljobclient.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @RequestMapping("/healthCheck")
    public String healthCheck(){
        return "success";
    }

    @RequestMapping("/info")
    public String info(){return "success";}
}
