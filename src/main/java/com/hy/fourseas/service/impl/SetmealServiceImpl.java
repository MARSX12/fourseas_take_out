package com.hy.fourseas.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hy.fourseas.common.CustomException;
import com.hy.fourseas.common.R;
import com.hy.fourseas.dto.SetmealDto;
import com.hy.fourseas.entity.Setmeal;
import com.hy.fourseas.entity.SetmealDish;
import com.hy.fourseas.mapper.SetmealMapper;
import com.hy.fourseas.service.SetmealDishService;
import com.hy.fourseas.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;


//    @Autowired
//    private SetmealService setmealService;


    public void saveWithDish(SetmealDto setmealDto){

        //保存套餐的基本信息，操作setmeal，执行insert操作
        this.save(setmealDto);

        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

        //保存套餐和菜品的关联信息，操作setmeal_dish,执行insert操作
        setmealDishService.saveBatch(setmealDishes);
    }


    /**
     * 删除套餐，同时需要删除套餐和菜品的关联数据
     * @param ids
     */

    public void removeWithDish(List<Long> ids) {
        //select count(*) from setmeal where id in (1,2,3) and status = 1
        //查询套餐状态，确定是否可用删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);

        int count = this.count(queryWrapper);
        if(count > 0){
            //如果不能删除，抛出一个业务异常
            throw new CustomException("套餐正在售卖中，不能删除");
        }

        //如果可以删除，先删除套餐表中的数据---setmeal
        this.removeByIds(ids);

        //delete from setmeal_dish where setmeal_id in (1,2,3)
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,ids);
        //删除关系表中的数据----setmeal_dish
        setmealDishService.remove(lambdaQueryWrapper);
    }

    @Override
    public void updateSetmealStatusById(Integer status,  List<Long> ids) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.in(ids !=null,Setmeal::getId,ids);
        List<Setmeal> list = this.list(queryWrapper);

        for (Setmeal setmeal : list) {
            if (setmeal != null){
                setmeal.setStatus(status);
                this.updateById(setmeal);
            }
        }
    }


//    @Override
//    public void updateWithDish(SetmealDto setmealDto){
//        if (setmealDto==null){
//            return R.error("请求异常");
//        }
//
//        if (setmealDto.getSetmealDishes()==null){
//            return R.error("套餐没有菜品,请添加套餐");
//        }
//        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
//        Long setmealId = setmealDto.getId();
//
//        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(SetmealDish::getSetmealId,setmealId);
//        setmealDishService.remove(queryWrapper);
//
//        //为setmeal_dish表填充相关的属性
//        for (SetmealDish setmealDish : setmealDishes) {
//            setmealDish.setSetmealId(setmealId);
//        }
//        //批量把setmealDish保存到setmeal_dish表
//        setmealDishService.saveBatch(setmealDishes);
//        setmealService.updateById(setmealDto);
//
//        return R.success("套餐修改成功");
//    }
/*
    public void updateWithDishes(SetmealDto setmealDto) {
        setmealMapper.updateById(setmealDto);
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId,setmealDto.getId());
        setmealDishMapper.delete(queryWrapper);
        Long id = setmealDto.getId();
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(id);
            setmealDishMapper.insert(setmealDish);
        }
        String image = setmealDto.getImage();
        redisUtils.save2Db(image);
    }

    @Override
    public void updateStatusById(Long id) {
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Setmeal::getId,id);
        Setmeal setmeal = setmealMapper.selectById(id);
        if (setmeal.getStatus() == 0 ){
            setmeal.setStatus(1);
        }else{
            setmeal.setStatus(0);
        }
        setmealMapper.updateById(setmeal);
    }


    */

    public SetmealDto getByIdWithDishs(Long id){
        Setmeal setmeal=this.getById(id);
        SetmealDto setmealDto=new SetmealDto();
        BeanUtils.copyProperties(setmeal,setmealDto);

        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getDishId,setmeal.getId());
        List<SetmealDish> setmealDishes=setmealDishService.list(queryWrapper);
        setmealDto.setSetmealDishes(setmealDishes);

        return setmealDto;
    }



}
