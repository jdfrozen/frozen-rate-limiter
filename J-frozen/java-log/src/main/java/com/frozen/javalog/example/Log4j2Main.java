package com.frozen.javalog.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Auther: Frozen
 * @Date: 2019/1/5 21:12
 * @Description:
 */
public class Log4j2Main {

    private static Logger log = LogManager.getLogger(Log4jMain.class);
    public static void main(String[] args){
        log.info("this is Log4j");
    }
}