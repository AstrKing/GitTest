package com.example.tiaoyou.mode.price.decorator.base;

import com.example.tiaoyou.mode.price.bean.order.OrderDetail;

import java.math.BigDecimal;

/**
 * 计算支付金额接口类
 * @author tr.wang
 *
 */
public interface IBaseCount {
	
	BigDecimal countPayMoney(OrderDetail orderDetail);

}
