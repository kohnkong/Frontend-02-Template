package io.kohnkong.spring02;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月17日 22:58
 */
@Component
@Aspect
public class LogAop {
    //切点(第一个*表示返回值，第二个*表示任意类，第三个*表示任意方法，(..)表示任意参数)
    @Pointcut("execution(* io.kohnkong.spring02.*.*(..))")
    public void demo(){}

    //前置通知
    @Before("demo()")
    public void before(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"方法开始执行...");
    }

    //后置通知
    @After("demo()")
    public void after(JoinPoint joinPoint){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"方法执行结束...");
    }

    //返回通知
    @AfterReturning(value = "demo()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"方法返回值为: "+result);
    }

    //异常通知
    @AfterThrowing(value = "demo()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e){
        String name = joinPoint.getSignature().getName();
        System.out.println(name+"方法发生异常，异常是: "+e.getMessage());
    }

    //环绕通知
    @Around("demo()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        return proceedingJoinPoint.proceed();
    }
}
