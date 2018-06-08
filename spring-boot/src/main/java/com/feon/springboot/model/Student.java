package com.feon.springboot.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * Package: com.feon.springboot.model
 * FileName: Student
 * Date: on 2018/6/6  上午11:01
 * Auther: Wally
 * Descirbe:学生表映射实体
 */
public class Student {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String gender;
    private String age;
    private String isDelete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", isDelete='" + isDelete + '\'' +
                '}';
    }
}