package com.hy.fourseas.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.fourseas.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
