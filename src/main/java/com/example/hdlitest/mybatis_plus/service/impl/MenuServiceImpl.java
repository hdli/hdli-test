package com.example.hdlitest.mybatis_plus.service.impl;

import com.example.hdlitest.mybatis_plus.entity.Menu;
import com.example.hdlitest.mybatis_plus.mapper.MenuMapper;
import com.example.hdlitest.mybatis_plus.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author lihuidong
 * @since 2019-12-17
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
