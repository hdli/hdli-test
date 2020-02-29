package com.example.hdlitest.designMode.strategy;

import java.math.BigDecimal;

public interface StrategyInterface {
    /**
     * 更具不同的支付渠道 计算商品价格
     * @param goodsId 商品ID
     * @return        优惠价格
     */
    BigDecimal calculate(int goodsId);
}
