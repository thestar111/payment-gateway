/**
 * 文 件 名:  OrderService
 * 描    述:  <描述>
 * 修 改 人:  zhouping
 * 修改时间:  15:05
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.bluetoop.payment.provider.service;

/**
 * <订单接口服务>
 *
 * @author zhouping
 * @version 1.0
 * @date 2021/5/30 15:05
 * @see [相关类/方法]
 * @since JDK 1.8
 */
public interface OrderService<R> {

    /**
     * 查询订单信息
     *
     * @param outTradeNo 微信订单ID
     * @param orderNo    商户系统订单ID
     * @return
     */
    R queryOrder(String outTradeNo, String orderNo);

}
