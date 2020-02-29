package com.example.hdlitest;

import com.example.hdlitest.mybatis_plus.entity.Admin;
import com.example.hdlitest.mybatis_plus.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HdliTestApplicationTests {

    @Autowired
    private AdminService adminService;

    @Test
    public void contextLoads() {
        Admin byId = adminService.getById(1);
        System.out.println(byId.toString());
    }

}
