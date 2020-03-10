package com.example.hdlitest.designMode.strategy.impl;

import com.example.hdlitest.designMode.strategy.StrategyInterface;
import com.example.hdlitest.designMode.strategy.annotation.Pay;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 微信支付
 * @author 李会东
 * @version 1.0
 * @date 2019-12-6 0:00
 */
@Pay(channleId = 2)
@Service
public class WeChatPayStrategyImpl implements StrategyInterface {
    @Override
    public BigDecimal calculate(int goodsId) {
        //更具商品ID获取商品价格和 相关优惠 计算获取用户应付金额
        return new BigDecimal(101);
    }
}
