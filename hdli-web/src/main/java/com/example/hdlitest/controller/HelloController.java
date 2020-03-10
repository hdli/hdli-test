package com.example.hdlitest.controller;

import com.example.hdlitest.tool.rateLimiter.AccessLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 李会东
 * @version 1.0
 * @date 2019-12-12 22:33
 */
@RestController
@RequestMapping("/guavalimitdemo")
public class HelloController {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private AccessLimitService accessLimitService;

    @RequestMapping("/access")
    public String access(){
        //尝试获取令牌
        if(accessLimitService.tryAcquire()){
            //模拟业务执行500毫秒
            try {
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return "aceess success [" + sdf.format(new Date()) + "]";
        }else{
            return "aceess limit [" + sdf.format(new Date()) + "]";
        }
    }
}
