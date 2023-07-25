package com.serendipity.myold.annotation.practice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * ClassName LogAspect
 * Description TODO
 * Author 11931
 * Date 2023-02-05:13:13
 * Version 1.0
 **/
@Aspect
@Component
public class LogAspect {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    /**
     * 配置织入点 - 自定义注解的包路径
     */
    @Pointcut("@annotation(com.old.annotation.practice.aop.Log)")
    public void logPointCut() {

    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint
     * @param jsonResult
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e         异常
     */
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }


    private void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {

            // 获得Log注解
            Log annotationLog = getAnnotationLog(joinPoint);
            if (annotationLog == null) {
                return;
            }

            // 获取当前用

        } catch (Exception exp) {
            log.error("==前置通知异常==");
            log.error("异常信息：{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     *
     * @param joinPoint 连接点
     * @return
     * @throws Exception
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
