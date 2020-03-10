package com.example.hdlitest.designMode.strategy;

import com.example.hdlitest.designMode.strategy.factory.StrategyFactory;

import java.math.BigDecimal;

/**
 * 计算商品价格上下文
 *
 * @author 李会东
 * @version 1.0
 * @date 2019-12-6 0:02
 */
public class StrategyContext {

    private StrategyFactory strategyFactory = StrategyFactory.getInstance();

    public BigDecimal calculate(int channle, int goodsId){
        StrategyInterface strategyInterface = strategyFactory.creat(channle);
        return strategyInterface.calculate(goodsId);
    }
}
