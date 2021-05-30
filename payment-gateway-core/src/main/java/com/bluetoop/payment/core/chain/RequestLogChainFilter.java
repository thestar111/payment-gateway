package com.bluetoop.payment.core.chain;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.ThreadContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

import static com.bluetoop.payment.core.cons.Constans.TRANCE_ID;
import static com.bluetoop.payment.core.cons.type.RequestHeader.X_REQUEST_ID;

/**
 * <请求过滤器>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/22 3:58 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Slf4j
@WebFilter(urlPatterns = "/api/*", filterName = "requestFilter")
public class RequestLogChainFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("【RequestFilter】init filter...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestId = request.getHeader(X_REQUEST_ID.getName());
        ThreadContext.put(TRANCE_ID, StringUtils.isEmpty(requestId) ? UUID.randomUUID().toString().replace("-", "") : requestId);
        filterChain.doFilter(request, servletResponse);
        ThreadContext.clearAll();
    }

    @Override
    public void destroy() {
        log.info("【RequestFilter】destroy filter...");
    }
}
