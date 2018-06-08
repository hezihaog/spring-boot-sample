package com.feon.springboot.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.feon.springboot.model.Student;


/**
 * Package: com.feon.springboot.service
 * FileName: IStudentService
 * Date: on 2018/6/6  上午11:04
 * Auther: Wally
 * Descirbe:学生表业务层接口
 */
public interface IStudentService extends IService<Student> {
    /**
     * 查询条件封装到student实体进行条件查询，返回
     */
    Student findObjByCondition(Student student);

    /**
     * 查询所有带分页
     */
    Page<Student> findAllByPage(Page<Student> page);
}