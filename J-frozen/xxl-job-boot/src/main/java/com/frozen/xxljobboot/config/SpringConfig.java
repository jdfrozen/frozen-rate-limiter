package com.frozen.xxljobboot.config;

import com.frozen.xxljobboot.controller.resolver.WebExceptionResolver;
import com.frozen.xxljobboot.core.schedule.XxlJobDynamicScheduler;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@Import(CommonConfig.class)
public class SpringConfig {

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Autowired
    DataSource dataSource;

    @Autowired
    Scheduler scheduler;

    @Bean
    public WebExceptionResolver getWebExceptionResolver() {
        return new WebExceptionResolver();
    }

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public XxlJobDynamicScheduler getXxlJobDynamicScheduler() throws Exception {
        XxlJobDynamicScheduler xxlJobDynamicScheduler = new XxlJobDynamicScheduler();
        xxlJobDynamicScheduler.setScheduler(scheduler);
        xxlJobDynamicScheduler.setAccessToken("");
        return xxlJobDynamicScheduler;
    }

}
