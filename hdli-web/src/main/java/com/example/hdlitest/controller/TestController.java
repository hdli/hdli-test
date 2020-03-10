package com.example.hdlitest.controller;

import com.example.Hello;
import com.example.hdlitest.spring_event.event.TaskFinishEvent2;
import com.example.hdlitest.spring_event.publisher.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-10 23:00
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @Autowired
    private Hello hello;

    /**
     * 测试 注解 切面实现重试
     * @return
     */
    @RequestMapping("/aaa")
    public String test(){
        testService.retry();
        return "aaa";
    }

    @RequestMapping("/bbb")
    public String testLock() throws InterruptedException {
        String bbb = testService.redissonLock();
        return bbb;
    }

    /**
     * 手动写的starter包 中的hello 类 自动装配到spring IOC容器中了
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/ccc")
    public String hello() throws InterruptedException {
        hello.welcome();
        return "ccc";
    }

    /**
     * 测试事件发布
     * @param msg
     * @return
     */
    @RequestMapping("/ddd")
    public String publisher(@RequestParam("msg")String msg){
//        EventPublisher2.publishEvent(new TaskFinishEvent2(msg));
        EventPublisher.publishEvent(new TaskFinishEvent2(msg));
        return msg;
    }


}
