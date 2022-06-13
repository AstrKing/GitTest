package com.example.tiaoyou.mode.price.factory;

import com.example.tiaoyou.mode.price.bean.SupportPromotions;
import com.example.tiaoyou.mode.price.bean.order.OrderDetail;
import com.example.tiaoyou.mode.price.decorator.CouponDecorator;
import com.example.tiaoyou.mode.price.decorator.DiscountDecorator;
import com.example.tiaoyou.mode.price.decorator.RedPacketDecorator;
import com.example.tiaoyou.mode.price.decorator.base.BaseCount;
import com.example.tiaoyou.mode.price.decorator.base.IBaseCount;
import com.example.tiaoyou.mode.price.enums.PromotionType;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 计算促销后的支付价格
 * @author tr.wang
 *
 */
public class PromotionFactory {

  public static BigDecimal getPayMoney(OrderDetail orderDetail) {

    // 获取给商品设定的促销类型
    List<SupportPromotions> supportPromotionslist =
        orderDetail.getMerchandise().getSupportPromotionsList();
    List<SupportPromotions> supportPromotionsSortedList = supportPromotionslist.stream()
            .sorted(Comparator.comparing(SupportPromotions::getPriority))
            .collect(Collectors.toList());
    // 初始化计算类
    IBaseCount baseCount = new BaseCount();
    if (supportPromotionsSortedList.size() > 0) {
      // 遍历设置的促销类型，通过装饰器组合促销类型
      for (SupportPromotions supportPromotions : supportPromotionsSortedList) {
        baseCount = protmotion(supportPromotions, baseCount);
      }
    }
    return baseCount.countPayMoney(orderDetail);
  }

  /**
   * 组合促销类型
   *
   * @param supportPromotions
   * @param baseCount
   * @return
   */
  private static IBaseCount protmotion(SupportPromotions supportPromotions, IBaseCount baseCount) {
    if (supportPromotions.getPromotionType() == PromotionType.COUPON) {
      baseCount = new CouponDecorator(baseCount, supportPromotions);
    } else if (supportPromotions.getPromotionType() == PromotionType.REDPACKED) {
      baseCount = new RedPacketDecorator(baseCount, supportPromotions);
    } else if (supportPromotions.getPromotionType() == PromotionType.DISCOUNT) {
      baseCount = new DiscountDecorator(baseCount, supportPromotions);
    }
    return baseCount;
  }
}
