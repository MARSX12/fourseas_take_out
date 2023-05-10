package com.hy.fourseas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hy.fourseas.common.CustomException;
import com.hy.fourseas.entity.Category;
import com.hy.fourseas.entity.Dish;
import com.hy.fourseas.entity.Setmeal;
import com.hy.fourseas.mapper.CategoryMapper;
import com.hy.fourseas.service.CategoryService;
import com.hy.fourseas.service.DishService;
import com.hy.fourseas.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 查看分类是否关联菜品，关联则无法删除
     * @param id
     */
    @Override
    public void remove(Long id){
        //通过分类ID进行查询
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        //有值则关联了菜品，抛出业务异常
        if(count1 > 0){
            throw new CustomException("当前分类关联了菜品，不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);
        if(count2 > 0){
            throw new CustomException("当前分类关联了套餐，不能删除");
        }

        //正常删除分类
        super.removeById(id);

    }

}
