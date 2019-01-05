package com.frozen.javalog.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Auther: Frozen
 * @Date: 2019/1/5 21:12
 * @Description:
 */

public class JclMain {

    private static Log log = LogFactory.getLog(JclMain.class);

    public static void main(String[] args){
        log.info("this is Jcl");
    }
}
