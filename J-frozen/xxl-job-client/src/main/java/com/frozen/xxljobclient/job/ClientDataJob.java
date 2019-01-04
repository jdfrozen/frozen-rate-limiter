package com.frozen.xxljobclient.job;


import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

@JobHandler(value = "xxl-job-client-job")
@Component
@Slf4j
public class ClientDataJob extends IJobHandler {


    @Override
    public ReturnT<String> execute(String param) throws Exception {

        XxlJobLogger.log("XXL-JOB api-server-statistical-job, Start.");
        try {
            log.info("执行时间："+new Date());
        } catch (Exception e) {
            XxlJobLogger.log("XXL-JOB, api-server-statistical-job Failed." , e);
            log.error("xxl-job, api-server-statistical-job failed", e);
            return FAIL;
        }
        return SUCCESS;
    }

}
