package com.hy.fourseas.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hy.fourseas.common.R;
import com.hy.fourseas.dto.OrdersDto;
import com.hy.fourseas.entity.OrderDetail;
import com.hy.fourseas.entity.Orders;
import com.hy.fourseas.service.OrderDetailService;
import com.hy.fourseas.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 订单
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("订单数据：{}",orders);
        orderService.submit(orders);
        return R.success("下单成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String number, String beginTime, String endTime){

        Page pageInfo = new Page<>(page,pageSize);
        return orderService.pageQuery(pageInfo,number,beginTime,endTime);
    }
    @PutMapping
    public R<String> status(@RequestBody OrdersDto ordersDto){
        orderService.updateStatusById(ordersDto);
        return R.success("修改订单状态成功！");
    }

    @GetMapping("/userPage")
    public R<Page> userPage(int page,int pageSize){
        Page pageInfo = new Page<>(page,pageSize);
        return orderService.userPage(pageInfo);
    }


    @DeleteMapping
    @Transactional(rollbackFor = Exception.class)
    public R<String> deleteOrder(Long id){
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOrderId,id);
        orderDetailService.remove(queryWrapper);
        orderService.removeById(id);
        return R.success("删除成功！");
    }


    @PostMapping("/again")
    public R<String> addOrderAgain(@RequestBody Orders orders){
        if (orders.getId() != null){
            return R.success("成功！");
        }
        return R.error("失败!");
    }

    @GetMapping("/getToDayOrder")
    public R<Long> getToDayOrder(){
        return orderService.countToDayOrder();
    }

    @GetMapping("/getYesDayOrder")
    public R<Long> getYesDayOrder(){
        return orderService.countYesDayOrder();
    }

    @GetMapping("/getOneWeekLiuShui")
    public R<Map> getOneWeekLiuShui(){
        return orderService.OneWeekLiuShui();
    }

    @GetMapping("/getOneWeekOrder")
    public R<Map> getOneWeekOrder(){
        return orderService.OneWeekOrder();
    }

    @GetMapping("/getHotSeal")
    public R<Map> getHotSeal(){
        return orderService.hotSeal();
    }




}