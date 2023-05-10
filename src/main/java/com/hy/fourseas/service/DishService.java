package com.hy.fourseas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.fourseas.dto.DishDto;
import com.hy.fourseas.entity.Dish;
import org.springframework.transaction.annotation.Transactional;

public interface DishService extends IService<Dish> {

    //新增菜品，同时插入菜品对应的口味数据，需要操作两张表：dish、dish_flavor
    @Transactional
    public void saveWithFlavor(DishDto dishDto);

    public DishDto getByIdWithFlavor(Long id);

    @Transactional
    public void updateWithFlavor(DishDto dishDto);

    @Transactional
    public void deleteWithFlavor(DishDto dishDto);

}
