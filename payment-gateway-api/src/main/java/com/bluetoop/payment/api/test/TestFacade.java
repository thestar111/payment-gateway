package com.bluetoop.payment.api.test;

import com.bluetoop.payment.core.common.Result;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <一句话功能描述>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/22 3:04 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@RequestMapping("/api/test")
public interface TestFacade {

    /**
     * 测试
     *
     * @param name
     * @return
     */
    @GetMapping(path = "/say", produces = MediaType.APPLICATION_JSON_VALUE)
    Result<String> test(@RequestParam("name") String name);
}
