package com.frozen.javalog.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: 冯默风
 * @Date: 2019/1/5 21:14
 * @Description:
 */
public class Sl4jMain {
    private static Logger log = LoggerFactory.getLogger(Sl4jMain.class);

    public static void main(String[] args){
        log.info("this is Sl4j");
    }
}
