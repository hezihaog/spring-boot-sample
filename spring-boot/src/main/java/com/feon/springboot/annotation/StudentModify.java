package com.feon.springboot.annotation;

import java.lang.annotation.*;

/**
 * Package: com.feon.springboot.annotation
 * FileName: StudentModify
 * Date: on 2018/6/7  上午9:16
 * Auther: Wally
 * Descirbe:学生表增删改方法标识注解，用此注解标识的方法，会被AOP拦截，必须传入StudentId
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface StudentModify {

}