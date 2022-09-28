package com.chrisspace.fancymall.order.dao;

import com.chrisspace.fancymall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-28 21:31:54
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
