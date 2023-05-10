package com.hy.fourseas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.fourseas.dto.SetmealDto;
import com.hy.fourseas.entity.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    @Transactional
    public void saveWithDish(SetmealDto setmealDto);

    @Transactional
    public void removeWithDish(List<Long> ids);


    void updateSetmealStatusById(Integer status,List<Long> ids);

//    @Transactional
//    public void updateWithDish(SetmealDto setmealDto);


    SetmealDto getByIdWithDishs(Long id);
}
