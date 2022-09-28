package com.chrisspace.fancymall.coupon.dao;

import com.chrisspace.fancymall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-28 21:04:12
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}