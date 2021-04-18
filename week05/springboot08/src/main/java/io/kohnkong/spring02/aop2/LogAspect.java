package io.kohnkong.spring02.aop2;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Description: TODO
 * @author: kongqf
 * @date: 2021年04月18日 09:15
 */
@Aspect//标记类为Aspect类
@Component//引入到Spring容器中
@Slf4j
public class LogAspect {
//    @Autowired
//    private MsgProducer msgProducer;

    @Pointcut("@annotation(io.kohnkong.spring02.aop2.OperateLog)")
    public void operateLog(){
        System.out.println("operateLog  1111");
    }

    //定义在http请求到方法之前将内容记录下来
    @Around("operateLog()")
    public Object doPointCut(ProceedingJoinPoint joinPoint) throws Throwable {
        OperateLogBean operateLog = new OperateLogBean();
        buildOperateObj(joinPoint, operateLog);
        boolean isCatchExcept = false;
        Exception e = null;
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception ex) {
            log.error("response exception: {}", ex.getMessage());
            operateLog.setResult(false);
            isCatchExcept = true;
            e = ex;
        }
        operateLog.setResult(true);
        // 用测试发送rabbitmq消息到日志微服务（此处为模拟）
        String massage = JSONObject.toJSONString(operateLog);
//        msgProducer.send2DirectTestQueue(massage);
        log.info(massage);
        if (isCatchExcept) {
            throw e;
        } else {
            return result;
        }
    }

    //日志转换国际化
    private void buildOperateObj(ProceedingJoinPoint joinPoint, OperateLogBean operateLog) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OperateLog ol = method.getAnnotation(OperateLog.class);
        if (ol != null) {
            operateLog.setType(I18nProperties.getI18nString(ol.type()));
            operateLog.setOperateObj(I18nProperties.getI18nString(ol.operateObj()));
        }
    }
}
