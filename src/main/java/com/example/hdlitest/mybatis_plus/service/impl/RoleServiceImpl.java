package com.example.hdlitest.mybatis_plus.service.impl;

import com.example.hdlitest.mybatis_plus.entity.Role;
import com.example.hdlitest.mybatis_plus.mapper.RoleMapper;
import com.example.hdlitest.mybatis_plus.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author lihuidong
 * @since 2019-12-17
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
