package com.example.hdlitest.mybatis_plus.controller;


import com.example.hdlitest.mybatis_plus.entity.Role;
import com.example.hdlitest.mybatis_plus.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author lihuidong
 * @since 2019-12-17
 */
@RestController
@RequestMapping("/mybatis_plus/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/quertList")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Role> queryList(){

        return roleService.list();
    }
}
