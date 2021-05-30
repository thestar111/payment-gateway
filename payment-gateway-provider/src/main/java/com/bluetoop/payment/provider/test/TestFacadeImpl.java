package com.bluetoop.payment.provider.test;

import com.bluetoop.payment.api.test.TestFacade;
import com.bluetoop.payment.core.common.Result;
import com.bluetoop.payment.core.cons.type.PrintLog;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <测试>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/22 3:10 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@RestController
public class TestFacadeImpl implements TestFacade {

    @PrintLog
    @Override
    public Result<String> test(@RequestParam("name") String name) {
        System.out.println(name);
        return new Result<>();
    }
}
