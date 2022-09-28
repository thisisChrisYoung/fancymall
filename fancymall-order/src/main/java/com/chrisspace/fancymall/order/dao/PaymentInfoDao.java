package com.chrisspace.fancymall.order.dao;

import com.chrisspace.fancymall.order.entity.PaymentInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表
 * 
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-28 21:31:54
 */
@Mapper
public interface PaymentInfoDao extends BaseMapper<PaymentInfoEntity> {
	
}
