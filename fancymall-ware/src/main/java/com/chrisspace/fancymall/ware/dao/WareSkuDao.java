package com.chrisspace.fancymall.ware.dao;

import com.chrisspace.fancymall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-28 21:39:05
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}
