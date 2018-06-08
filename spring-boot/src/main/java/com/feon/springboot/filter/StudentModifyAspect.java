package com.feon.springboot.filter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Package: com.feon.springboot.filter
 * FileName: StudentModifyAspect
 * Date: on 2018/6/7  上午9:19
 * Auther: Wally
 * Descirbe:
 */
@Aspect
@Component
public class StudentModifyAspect {
    @Pointcut("@annotation(com.feon.springboot.annotation.StudentModify)")
    public void annotationPoint() {
    }

    @Around("@annotation(com.feon.springboot.annotation.StudentModify)")
    public Object processTxTest(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("before annotationPoint --> " + Arrays.asList(joinPoint.getArgs()));
        Object result = joinPoint.proceed(joinPoint.getArgs());
        System.out.println("after annotationPoint --> " + Arrays.asList(joinPoint.getArgs()));
        return result;
    }
}