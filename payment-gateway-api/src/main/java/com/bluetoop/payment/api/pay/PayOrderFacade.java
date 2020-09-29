package com.bluetoop.payment.api.pay;

import com.bluetoop.payment.api.pay.request.PayOrderRequest;
import com.bluetoop.payment.core.common.Result;
import com.bluetoop.payment.core.strategy.response.PayResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <订单支付>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/22 1:47 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@RequestMapping(path = "/api/order")
public interface PayOrderFacade {

    /**
     * 订单支付
     *
     * @param payOrderRequest
     * @return
     */
    @PostMapping(path = "/pay/v1")
    Result<PayResponse> pay(@RequestBody PayOrderRequest payOrderRequest);
}
