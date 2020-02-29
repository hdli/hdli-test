package com.example.hdlitest.mybatis_plus.service.impl;

import com.example.hdlitest.mybatis_plus.entity.Product;
import com.example.hdlitest.mybatis_plus.mapper.ProductMapper;
import com.example.hdlitest.mybatis_plus.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lihuidong
 * @since 2019-12-17
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
