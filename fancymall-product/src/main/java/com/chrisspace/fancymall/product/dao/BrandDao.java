package com.chrisspace.fancymall.product.dao;

import com.chrisspace.fancymall.product.entity.BrandEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 品牌
 * 
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-25 22:55:16
 */
@Mapper
public interface BrandDao extends BaseMapper<BrandEntity> {
	
}
