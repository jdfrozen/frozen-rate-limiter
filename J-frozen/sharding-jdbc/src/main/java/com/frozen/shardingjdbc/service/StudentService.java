package com.frozen.shardingjdbc.service;

import com.frozen.shardingjdbc.dao.StudentMapper;
import com.frozen.shardingjdbc.po.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @Auther: Frozen
 * @Date: 2019/1/3 19:19
 * @Description:
 */
@Service
public class StudentService{

    @Resource
    public StudentMapper studentMapper;

    public boolean insert(Student student) {
        return studentMapper.insert(student) > 0 ? true : false;
    }

}
