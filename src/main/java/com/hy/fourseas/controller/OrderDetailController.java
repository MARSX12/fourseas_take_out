package com.hy.fourseas.controller;

import com.hy.fourseas.common.R;
import com.hy.fourseas.entity.OrderDetail;
import com.hy.fourseas.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单明细
 */
@Slf4j
@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/{id}")
    public R<List<OrderDetail>> getById(@PathVariable Long id){

        return orderDetailService.getByOrderId(id);

    }

}