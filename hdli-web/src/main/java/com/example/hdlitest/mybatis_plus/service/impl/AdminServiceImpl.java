package com.example.hdlitest.mybatis_plus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hdlitest.mybatis_plus.entity.Admin;
import com.example.hdlitest.mybatis_plus.mapper.AdminMapper;
import com.example.hdlitest.mybatis_plus.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author lihuidong
 * @since 2019-12-17
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
