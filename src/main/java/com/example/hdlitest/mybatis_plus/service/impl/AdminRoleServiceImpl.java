package com.example.hdlitest.mybatis_plus.service.impl;

import com.example.hdlitest.mybatis_plus.entity.AdminRole;
import com.example.hdlitest.mybatis_plus.mapper.AdminRoleMapper;
import com.example.hdlitest.mybatis_plus.service.AdminRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色映射表 服务实现类
 * </p>
 *
 * @author lihuidong
 * @since 2019-12-17
 */
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {


    public void test(){
        this.baseMapper.selectList(null);
    }
}
