package com.bluetoop.payment.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * <入口>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/22 3:13 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.bluetoop.payment")
@ServletComponentScan(basePackages = "com.bluetoop.payment.core.chain")
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }
}
