package com.frozen.shardingjdbc.dao;

import com.frozen.shardingjdbc.po.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Auther: Frozen
 * @Date: 2019/1/3 19:21
 * @Description:
 */
@Mapper
public interface StudentMapper {
    /**
     * 插入学生
     * @param s
     * @return
     */
    Integer insert(Student s);

    /**
     * 查询所有学生
     * @return
     */
    List<Student> findAll();

    /**
     * 根据学生ID列表查询学生列表
     * @param studentIds
     * @return
     */
    List<Student> findByStudentIds(List<Integer> studentIds);
}
