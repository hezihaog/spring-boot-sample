package com.feon.springboot.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.feon.springboot.annotation.StudentModify;
import com.feon.springboot.common.BaseResult;
import com.feon.springboot.model.Student;
import com.feon.springboot.model.vo.StudentVO;
import com.feon.springboot.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.feon.springboot.controller
 * FileName: StudentController
 * Date: on 2018/6/6  下午2:15
 * Auther: Wally
 * Descirbe:学生表请求处理器
 */

//RestController标识是Rest风格的Controller，请求处理器
@RestController
//RequestMapping标识student路径下的请求都找这个处理器
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService mService;

    /**
     * 查询学生接口，GetMapping标识是get请求
     */
    @ResponseBody
    @GetMapping(value = "/get")
    public BaseResult<StudentVO> getStudent(@RequestParam String studentId) {
        Student student = mService.selectById(studentId);
        if (!student.getIsDelete().equals("1")) {
            return new BaseResult<StudentVO>(-1, "请求的studentId已被删除", null);
        }
        StudentVO studentVO = new StudentVO();
        studentVO.setStudentId(student.getId());
        studentVO.setName(student.getName());
        studentVO.setGender(student.getGender());
        studentVO.setAge(student.getAge());
        return new BaseResult<StudentVO>(studentVO);
    }

    /**
     * 查询所有学生的信息列表
     *
     * @param page 查询哪一页
     * @param size 一页多少条
     */
    @ResponseBody
    @GetMapping(value = "/getAll")
    public BaseResult<List<StudentVO>> getAll(@RequestParam(required = false, defaultValue = "1") int page, @RequestParam(required = false, defaultValue = "15") int size) {
        if (size <= 0) {
            size = 15;
        }
        Page<Student> p = new Page<Student>(page, size);
        p = mService.selectPage(p, new EntityWrapper<Student>().eq("isDelete", "1"));
        List<Student> allStudent = p.getRecords();
        List<StudentVO> resultList = new ArrayList<StudentVO>();
        for (Student student : allStudent) {
            StudentVO studentVO = new StudentVO();
            studentVO.setStudentId(student.getId());
            studentVO.setName(student.getName());
            studentVO.setGender(student.getGender());
            studentVO.setAge(student.getAge());
            resultList.add(studentVO);
        }
        return new BaseResult<List<StudentVO>>(resultList);
    }

    /**
     * 增加学生
     *
     * @param name   学生姓名
     * @param gender 学生性别
     * @param age    学生年龄
     */
    @StudentModify
    @ResponseBody
    @PostMapping(value = "/add")
    public BaseResult addStudent(@RequestParam String name, @RequestParam String gender, String age) {
        Student student = new Student();
        student.setName(name);
        student.setGender(gender);
        student.setAge(age);
        boolean isSuccess = mService.insert(student);
        BaseResult result = new BaseResult();
        if (isSuccess) {
            result.setCode(0);
            result.setMsg("success");
            return result;
        } else {
            result.setCode(-1);
            result.setMsg("增加失败");
        }
        return result;
    }

    /**
     * 更新学生
     *
     * @param studentId 学生id
     * @param name      学生姓名
     * @param gender    学生性别
     * @param age       学生年龄
     */
    @StudentModify
    @ResponseBody
    @PostMapping(value = "/update")
    public BaseResult updateStudent(@RequestParam String studentId, @RequestParam(required = false) String name,
                                    @RequestParam(required = false) String gender, @RequestParam(required = false) String age) {
        Student student = mService.selectById(studentId);
        student.setName(name);
        student.setGender(gender);
        student.setAge(age);
        boolean isSuccess = mService.updateById(student);
        BaseResult result = new BaseResult();
        if (isSuccess) {
            result.setCode(0);
            result.setMsg("success");
        } else {
            result.setCode(-1);
            result.setMsg("更新失败");
        }
        return result;
    }

    /**
     * 删除学生
     *
     * @param studentId 用户id
     */
    @StudentModify
    @ResponseBody
    @PostMapping(value = "/delete")
    public BaseResult deleteStudent(@RequestParam String studentId) {
        Student student = mService.selectById(studentId);
        student.setIsDelete("2");
        boolean isSuccess = mService.updateById(student);
        BaseResult result = new BaseResult();
        if (isSuccess) {
            result.setCode(0);
            result.setMsg("success");
        } else {
            result.setCode(-1);
            result.setMsg("删除失败");
        }
        return result;
    }
}