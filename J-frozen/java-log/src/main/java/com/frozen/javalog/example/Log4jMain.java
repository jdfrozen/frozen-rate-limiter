package com.frozen.javalog.example;

import org.apache.log4j.*;
/**
 * @Auther: Frozen
 * @Date: 2019/1/5 21:12
 * @Description:
 */
public class Log4jMain {
    private static Logger log = Logger.getLogger(Log4jMain.class);
    public static void main(String[] args){
        log.info("this is Log4j");
    }
}
