package com.chrisspace.fancymall.product.dao;

import com.chrisspace.fancymall.product.entity.ProductAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * spu属性值
 * 
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-25 22:55:16
 */
@Mapper
public interface ProductAttrValueDao extends BaseMapper<ProductAttrValueEntity> {
	
}
