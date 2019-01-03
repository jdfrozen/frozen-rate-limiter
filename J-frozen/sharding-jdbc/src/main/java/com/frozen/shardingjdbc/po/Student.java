package com.frozen.shardingjdbc.po;

import lombok.Data;
import lombok.ToString;

/**
 * @Auther: Frozen
 * @Date: 2019/1/3 19:20
 * @Description:
 */
@Data
@ToString
public class Student {
    private Integer id;
    private Integer studentId;
    private String name;
    private Integer age;
}
