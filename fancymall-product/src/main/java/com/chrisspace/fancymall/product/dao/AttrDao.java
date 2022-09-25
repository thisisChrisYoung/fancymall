package com.chrisspace.fancymall.product.dao;

import com.chrisspace.fancymall.product.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品属性
 * 
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-25 22:55:16
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {
	
}
