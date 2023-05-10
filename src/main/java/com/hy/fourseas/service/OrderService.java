package com.hy.fourseas.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hy.fourseas.common.R;
import com.hy.fourseas.dto.OrdersDto;
import com.hy.fourseas.entity.Orders;

import java.util.Map;


public interface OrderService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     */
    public void submit(Orders orders);

    /**
     * 后台订单明细分页查询
     * @param pageInfo
     * @param number
     * @param beginTime
     * @param endTime
     * @return
     */
    R<Page> pageQuery(Page pageInfo, String number, String beginTime, String endTime);

    /**
     * 通过订单号修改订单状态
     * @param ordersDto
     */
    void updateStatusById(OrdersDto ordersDto);

    /**
     * 用户订单分页查询
     * @param pageInfo
     * @return
     */
    R<Page> userPage(Page pageInfo);

    /**
     * 查询今日订单量
     * @return
     */
    R<Long> countToDayOrder();

    /**
     * 查询昨日订单量
     * @return
     */
    R<Long> countYesDayOrder();

    /**
     * 查询近一周流水
     * @return
     */
    R<Map> OneWeekLiuShui();

    /**
     * 查询近一周订单数量
     * @return
     */
    R<Map> OneWeekOrder();

    /**
     * 查询热卖套餐
     * @return
     */
    R<Map> hotSeal();
}
