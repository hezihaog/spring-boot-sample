package com.feon.springboot.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.feon.springboot.mapper.StudentMapper;
import com.feon.springboot.model.Student;
import com.feon.springboot.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.feon.springboot.service.impl
 * FileName: StudentServiceImpl
 * Date: on 2018/6/6  上午11:05
 * Auther: Wally
 * Descirbe:学生表业务层实现
 */

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    @Autowired
    private StudentMapper mStudentMapper;

    @Override
    public Student findObjByCondition(Student student) {
        return mStudentMapper.selectOne(student);
    }

    @Override
    public Page<Student> findAllByPage(Page<Student> page) {
        List<Student> all = mStudentMapper.findAllWithPage(page);
        page.setRecords(all);
        return page;
    }
}