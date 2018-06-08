package com.feon.springboot.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.feon.springboot.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Package: com.feon.springboot.mapper
 * FileName: StudentMapper
 * Date: on 2018/6/6  上午11:16
 * Auther: Wally
 * Descirbe:学生表的Mapper类，相当于Dao层，定义一些BaseMapper中无法解决的特殊方法，业务层实现类调用Mapper对象进行CURD
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {
    /**
     * 查询全部，带分页
     *
     * @param page 分页模型
     */
    List<Student> findAllWithPage(Pagination page);

    /**
     * 指定条件来查询，带分页
     */
    List<Student> findAllWithPageAndParamMap(Pagination page, String name, String age);
}