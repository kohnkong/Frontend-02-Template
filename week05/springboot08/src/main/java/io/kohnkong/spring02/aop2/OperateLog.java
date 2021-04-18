package io.kohnkong.spring02.aop2;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月18日 09:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface OperateLog {
    String type() default "";

    String operateObj() default "";
}
