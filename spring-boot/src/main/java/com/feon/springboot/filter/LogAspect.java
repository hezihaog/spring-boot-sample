package com.feon.springboot.filter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* com.feon.springboot.service.*.*(..))")
    public void pc() {}

    /*@Before("pc()")
    public void before() {
        System.out.println("before invoke......");
    }

    @After("pc()")
    public void after() {
        System.out.println("after invoke......");
    }*/

    @Around("pc()")
    public Object around(final ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("before invoke......");
        Object result = pjp.proceed();
        System.out.println("after invoke......");
        return result;
    }
}
