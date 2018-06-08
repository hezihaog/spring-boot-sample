package com.feon.springboot.filter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Package: com.feon.springboot.filter
 * FileName: TestAspect
 * Date: on 2018/6/8  上午11:57
 * Auther: Wally
 * Descirbe:
 */
@Aspect
@Component
public class TestAspect {
    /**
     * 在函数调用前回调
     */
    @Before("execution(* com.feon.springboot.service.impl.StudentServiceImpl.*(..))")
    public void beforeTest() {
        System.out.println("Before ---> StudentService 被调用方法啦...");
    }

    /**
     * 在函数调用后回调，不管是异常结束还是执行结束都会调用！类似finally的处理，在这里释放资源等
     */
    @After("execution(* com.feon.springboot.service.impl.StudentServiceImpl.*(..))")
    public void afterTest() {
        System.out.println("After ---> StudentService 被调用方法啦...");
    }

    /**
     * 可捕捉函数返回值，跟方法设置的形参有关，例如Object rvt，类型是Object，代表拦截所有，如果是String则只捕捉返回值为String类型的函数
     *
     * @param rvt 捕捉到的函数的返回值，会通过定义的方法的形参传入
     *            returning代表返回值赋值到形参的参数名，pointcut代表切入点条件
     */
    @AfterReturning(returning = "rvt", pointcut = "execution(* com.feon.springboot.service.impl.StudentServiceImpl.*(..))")
    public void afterReturnTest(Object rvt) {
        //只能获取返回值，不能修改
        System.out.println("方法执行返回值获取 ---> : " + rvt);
    }

    /**
     * 可抓取未处理抛出的异常，可在此回调中处理异常和获取异常信息
     *
     * @param ex 抛出的未处理的异常
     *           throwing代表异常存储到形参的名字
     */
    @AfterThrowing(throwing = "ex", pointcut = "execution(* com.feon.springboot.service.impl.StudentServiceImpl.*(..))")
    public void afterThrowingTest(Throwable ex) {
        System.out.println("未处理的异常信息： " + ex.toString());
        System.out.println("模拟处理异常结束...");
    }

    /**
     * 捕获方法之前前后
     */
    @Around("execution(* com.feon.springboot.service.impl.StudentServiceImpl.*(..))")
    public Object processTxTest(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        System.out.println("processTxTest 方法执行参数 --> " + Arrays.asList(args));
        return joinPoint.proceed();
    }

    /**
     * 织入执行顺序：Around -> Before -> Around -> AfterReturning -> After
     */
}