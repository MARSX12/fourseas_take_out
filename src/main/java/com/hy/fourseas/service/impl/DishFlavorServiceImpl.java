package com.hy.fourseas.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.fourseas.entity.DishFlavor;
import com.hy.fourseas.mapper.DishFlavorMapper;
import com.hy.fourseas.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
