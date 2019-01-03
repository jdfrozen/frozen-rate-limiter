package com.frozen.shardingjdbc.dao;

import com.frozen.shardingjdbc.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: Frozen
 * @Date: 2019/1/3 19:23
 * @Description:
 */
@Mapper
public interface UserMapper {
    /**
     * 插入用户
     * @param u
     * @return
     */
    Integer insert(User u);

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 根据用户ID列表查询用户列表
     * @param userIds
     * @return
     */
    List<User> findByUserIds(List<Integer> userIds);
}
