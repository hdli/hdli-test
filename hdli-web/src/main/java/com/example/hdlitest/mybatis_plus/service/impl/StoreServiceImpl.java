package com.example.hdlitest.mybatis_plus.service.impl;

import com.example.hdlitest.mybatis_plus.entity.Store;
import com.example.hdlitest.mybatis_plus.mapper.StoreMapper;
import com.example.hdlitest.mybatis_plus.service.StoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商铺 服务实现类
 * </p>
 *
 * @author lihuidong
 * @since 2021-07-02
 */
@Service
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store> implements StoreService {

}
