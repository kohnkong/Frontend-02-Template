package io.kohnkong.spring02.aop;

import io.kohnkong.spring02.School;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月17日 22:23
 */
@Aspect
@Component
public class Aop1 {

    @Pointcut("execution(* io.kohnkong.spring02.Klass.*dong(..))")
    public void point() {

    }
    @Before("point()")
    public void before() {
        System.out.println("========>begin klass dong...");
    }

    @AfterReturning("point()")
    public void after() {
        System.out.println("========>after klass dong...");
    }

    @Around("point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("========>around begin klass dong");
        joinPoint.proceed();
        System.out.println("========>around after klass dong");
    }
}
