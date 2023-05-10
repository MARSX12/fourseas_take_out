package com.hy.fourseas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.fourseas.entity.ShoppingCart;
import com.hy.fourseas.mapper.ShoppingCartMapper;
import com.hy.fourseas.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {

}
