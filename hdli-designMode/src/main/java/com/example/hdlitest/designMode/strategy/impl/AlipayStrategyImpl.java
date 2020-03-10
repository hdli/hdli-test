package com.example.hdlitest.designMode.strategy.impl;

import com.example.hdlitest.designMode.strategy.StrategyInterface;
import com.example.hdlitest.designMode.strategy.annotation.Pay;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 支付宝支付优惠
 * @author 李会东
 * @version 1.0
 * @date 2019-12-5 23:54
 */
@Pay(channleId = 1)
@Service
public class AlipayStrategyImpl implements StrategyInterface {

    @Override
    public BigDecimal calculate(int goodsId) {
        //更具商品ID获取商品价格和 相关优惠 计算获取用户应付金额
        return new BigDecimal(100);
    }
}
