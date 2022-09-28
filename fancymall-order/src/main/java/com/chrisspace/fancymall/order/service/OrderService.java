package com.chrisspace.fancymall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chrisspace.fancymall.common.utils.PageUtils;
import com.chrisspace.fancymall.order.entity.OrderEntity;

import java.util.Map;

/**
 * 订单
 *
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-28 21:31:54
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

