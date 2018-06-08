package com.feon.springboot;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.feon.springboot.model.Student;
import com.feon.springboot.service.IStudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Package: com.feon.springboot
 * FileName: StudentDaoTest
 * Date: on 2018/6/6  上午11:37
 * Auther: Wally
 * Descirbe:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentDaoTest {
    @Autowired
    IStudentService mStudentService;

    @Test
    public void testFindByObj() {
        Student student = new Student();
        student.setId(1);
        student = mStudentService.findObjByCondition(student);
        System.out.println(student);
    }

    @Test
    public void testFindById() {
        Page<Student> page = new Page<>(1, 15);
        Student student = mStudentService.selectById(1);
        Assert.assertNotNull(student);
        System.out.println(student.toString());
    }

    @Test
    public void testFindByPage() {
        Page<Student> page = new Page<Student>();
        //设置分页，第一页，每页15条
        page.setCurrent(1);
        page.setSize(15);
        Page<Student> all = mStudentService.findAllByPage(page);
        Assert.assertNotNull(all);
        Assert.assertNotNull(all.getRecords());
        for (Student student : all.getRecords()) {
            System.out.println(student.toString() + "\n");
        }
    }

    @Test
    public void testInsert() {
        for (int i = 0; i < 2; i++) {
            Student student = new Student();
            if (i == 0) {
                student.setName("王孝敏");
                student.setAge("22");
                student.setGender("女");
            } else {
                student.setName("王俊");
                student.setAge("28");
                student.setGender("男");
            }
            Assert.assertTrue(mStudentService.insertAllColumn(student));
        }
    }

    @Test
    public void testFindAllWithPageAndParamMap() {
        //设置分页，第一页，每页15条
        Page<Student> page = new Page<Student>(1, 15);
        //定义查询姓王的人，年龄在18和25之间，是>= & <=的
        Page<Student> all = mStudentService.selectPage(page,
                new EntityWrapper<Student>()
                        .like("name", "王%")
                        .between("age", 22, 28));
        Assert.assertNotNull(all);
        Assert.assertNotNull(all.getRecords());
        for (Student student : all.getRecords()) {
            System.out.println(student.toString() + "\n");
        }
    }
}