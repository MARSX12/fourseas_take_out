package com.hy.fourseas.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hy.fourseas.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

}