package com.example.tiaoyou.mode.price.decorator;

import com.example.tiaoyou.mode.price.bean.SupportPromotions;
import com.example.tiaoyou.mode.price.bean.order.OrderDetail;
import com.example.tiaoyou.mode.price.decorator.base.BaseCountDecorator;
import com.example.tiaoyou.mode.price.decorator.base.IBaseCount;

import java.math.BigDecimal;
import java.util.List;

/**
 * 计算打折后的金额
 *
 * @author tr.wang
 */
public class DiscountDecorator extends BaseCountDecorator {
  private SupportPromotions supportPromotions;

  public DiscountDecorator(IBaseCount count, SupportPromotions supportPromotions) {
    super(count);
    this.supportPromotions = supportPromotions;
  }

  public BigDecimal countPayMoney(OrderDetail orderDetail) {
    BigDecimal payTotalMoney = new BigDecimal(0);
    super.countPayMoney(orderDetail);
    payTotalMoney = countDiscountPayMoney(orderDetail);
    return payTotalMoney;
  }

  private BigDecimal countDiscountPayMoney(OrderDetail orderDetail) {
    System.out.println("--------------------------打折-------------------------");
    List<SupportPromotions> supportPromotionsList =
        orderDetail.getMerchandise().getSupportPromotionsList();
    double discount = 100;
    discount = supportPromotions.getUserDiscount().getDiscount();

    System.out.println("折扣：" + discount / 100);
    BigDecimal multiply = orderDetail.getPayMoney().multiply(new BigDecimal(discount / 100));
    orderDetail.setPayMoney(multiply.setScale(2, BigDecimal.ROUND_HALF_UP));
    System.out.println("折扣后商品价格：" + orderDetail.getPayMoney());
    System.out.println("  ");
    return orderDetail.getPayMoney();
  }
}
