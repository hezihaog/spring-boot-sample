package com.feon.springboot.model.vo;

/**
 * Package: com.feon.springboot.model.vo
 * FileName: StudentVO
 * Date: on 2018/6/6  下午3:56
 * Auther: Wally
 * Descirbe:学生表接口VO对象
 */
public class StudentVO {
    private Integer studentId;
    private String name;
    private String gender;
    private String age;

    public StudentVO() {
    }

    public StudentVO(Integer studentId, String name, String gender, String age) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
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
}