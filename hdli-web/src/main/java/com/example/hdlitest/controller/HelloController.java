package com.example.hdlitest.controller;

import com.example.hdlitest.tool.rateLimiter.AccessLimitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(tags = "限流测试")
public class HelloController {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private AccessLimitService accessLimitService;


    @ApiOperation(value = "测试接口", notes = "测试接口")
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

    @PostMapping("/test")
    public String test(){
        return "SUCCESS";
    }
}
