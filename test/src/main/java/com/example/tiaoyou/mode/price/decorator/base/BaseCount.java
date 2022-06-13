package com.example.tiaoyou.mode.price.decorator.base;

import com.example.tiaoyou.mode.price.bean.order.OrderDetail;

import java.math.BigDecimal;

/**
 * 支付基本类
 *
 * @author tr.wang
 */
public class BaseCount implements IBaseCount {

    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        orderDetail.setPayMoney(orderDetail.getMerchandise().getPrice());
        System.out.println("商品原单价金额为：" + orderDetail.getPayMoney());
        return orderDetail.getPayMoney();
    }

}
