package com.example.hdlitest.tool.guava.eventBus.spring;

import com.example.hdlitest.tool.guava.eventBus.spring.definition.EventListener;
import com.google.common.eventbus.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


/**
 *
 * 阿里妈妈群组同步的监听器
 * @author luyi
 * @date 2024/5/30 16:38
 */
@Component
@Slf4j
public class MamaSycListener implements EventListener {

//    @Resource
//    private HjcGroupService hjcGroupService;
//
//    @Resource
//    private CrowdService crowdService;


    @Subscribe
    public boolean handle(OdpsSycCompleteEvent mamaSycMsg) {
        log.warn("MamaSycListener handle, msg:{}", mamaSycMsg);
        System.out.println("业务执行");
        return true;
    }


}
