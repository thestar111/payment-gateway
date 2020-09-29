package com.bluetoop.payment.core.handler;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import static com.bluetoop.payment.core.cons.Constans.TRANCE_ID;
import static com.bluetoop.payment.core.type.RequestHeader.*;

/**
 * <消息统一处理>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/28 10:45 上午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@ControllerAdvice
public class ResponseBodyHandler implements ResponseBodyAdvice<Object> {

    /**
     * 是否支持注入
     *
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    /**
     * 响应头拦截
     *
     * @param body
     * @param methodParameter
     * @param mediaType
     * @param aClass
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        serverHttpResponse.getHeaders().add(X_REQUEST_ID.getName(), ThreadContext.get(TRANCE_ID));
        serverHttpResponse.getHeaders().add(X_APPLICATION_CONTEXT.getName(), "payment-gateway");
        serverHttpResponse.getHeaders().add(X_VIA.getName(), "lantuo-via");
        return body;
    }
}
