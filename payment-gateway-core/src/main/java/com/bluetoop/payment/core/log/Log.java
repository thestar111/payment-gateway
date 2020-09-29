package com.bluetoop.payment.core.log;

import com.bluetoop.payment.core.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * <日志切面>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/22 2:01 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Slf4j
@Aspect
@Component
public class Log {

    /**
     * 定义切入点
     */
    @Pointcut("@annotation(com.bluetoop.payment.core.type.PrintLog)")
    public void point() {
    }

    /**
     * 业务执行
     *
     * @param joinPoint
     */
    @Before("point()")
    public void execute(JoinPoint joinPoint) {
        // 执行controller 方法之前需要记录的请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("请求 URL: {}", request.getRequestURL());
        log.info("请求方法 HTTP_METHOD: {} ", request.getMethod());
        log.info("请求 IP: {}", request.getRemoteAddr());
        log.info("请求类 CLASS_METHOD: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("请求参数 ARGS: {}", Arrays.toString(joinPoint.getArgs()));
    }

    /**
     * 后置通知
     */
    @AfterReturning(returning = "result", pointcut = "point()")
    public void logResultVOInfo(Result result) {
        log.info("请求响应吗 ：【{}】 \t	响应结果 ：【{}】", result.getCode(), result);
    }
}
