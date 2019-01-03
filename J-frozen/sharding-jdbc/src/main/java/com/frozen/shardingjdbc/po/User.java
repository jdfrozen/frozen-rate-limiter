package com.frozen.shardingjdbc.po;

import lombok.Data;
import lombok.ToString;

/**
 * @Auther: Frozen
 * @Date: 2019/1/3 19:19
 * @Description:
 */
@Data
@ToString
public class User {
    private Integer id;
    private Integer userId;
    private String name;
    private Integer age;
}
