package com.chrisspace.fancymall.coupon.dao;

import com.chrisspace.fancymall.coupon.entity.MemberPriceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品会员价格
 * 
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-28 21:04:12
 */
@Mapper
public interface MemberPriceDao extends BaseMapper<MemberPriceEntity> {
	
}