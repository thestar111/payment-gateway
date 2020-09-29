package com.bluetoop.payment.provider.pay;

import com.bluetoop.payment.api.pay.PayOrderFacade;
import com.bluetoop.payment.api.pay.domain.ProductInfo;
import com.bluetoop.payment.api.pay.request.PayOrderRequest;
import com.bluetoop.payment.core.common.Result;
import com.bluetoop.payment.core.context.PaymentContext;
import com.bluetoop.payment.core.error.IErrorCode;
import com.bluetoop.payment.core.exception.PaymentException;
import com.bluetoop.payment.core.pay.domain.H5_info;
import com.bluetoop.payment.core.strategy.PayStrategy;
import com.bluetoop.payment.core.strategy.request.PayRequest;
import com.bluetoop.payment.core.strategy.response.PayResponse;
import com.bluetoop.payment.core.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * <订单支付接口>
 *
 * @author zhouping
 * @version 1.0
 * @date 2020/9/22 3:32 下午
 * @see [相关类/方法]
 * @since JDK 1.8
 */
@Slf4j
@RestController
public class PayOrderController implements PayOrderFacade {

    @Autowired
    private PaymentContext paymentContext;

    /**
     * 订单支付
     *
     * @param payOrderRequest
     * @return
     */
    @Override
    public Result<PayResponse> pay(@RequestBody PayOrderRequest payOrderRequest) {
        ProductInfo productInfo = Optional.ofNullable(payOrderRequest).map(s -> s.getProductInfo()).orElse(null);
        String orderid = Optional.ofNullable(payOrderRequest).map(s -> s.getOrderId()).orElse(null);
        String payType = Optional.ofNullable(payOrderRequest).map(s -> s.getPayType()).orElse(null);
        if (productInfo == null) {
            throw new PaymentException("产品信息为空", IErrorCode.INVALID_PARAMS_ERROR);
        }
        if (StringUtil.isEmpty(orderid)) {
            throw new PaymentException("订单号为空", IErrorCode.INVALID_PARAMS_ERROR);
        }
        if (StringUtil.isEmpty(payType)) {
            throw new PaymentException("支付类型为空", IErrorCode.INVALID_PARAMS_ERROR);
        }
        PayStrategy pay = paymentContext.creator(payOrderRequest.getPayType());
        PayRequest payRequest = new PayRequest();
        payRequest.setOutOrderNo(payOrderRequest.getOrderId());
        payRequest.setAmount(payOrderRequest.getAmount());
        payRequest.setAttach(productInfo.getProductName());
        payRequest.setBody(productInfo.getBody());
        payRequest.setIp(payOrderRequest.getIp());
        payRequest.setOpenId(payOrderRequest.getOpenId());
        payRequest.setProductName(productInfo.getProductName());
        H5_info h5_info = new H5_info();
        h5_info.setType("WAP");
        h5_info.setWap_name(productInfo.getProductName());
        payRequest.setH5_info(h5_info);
        log.info("【PayOrderController】invoke pay ===============================>>>> request : {}", payRequest);
        PayResponse payResponse = pay.pay(payRequest);
        log.info("【PayOrderController】invoke pay ===============================<<<< response : {}", payRequest);
        Result<PayResponse> result = new Result<>();
        result.setErrorCode(IErrorCode.SUCCESS);
        result.setData(payResponse);
        return result;
    }
}
