package com.chrisspace.fancymall.product.dao;

import com.chrisspace.fancymall.product.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品属性
 * 
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-25 22:55:16
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {

    List<AttrEntity> queryAttrsByIds(List<Long> attrIds);
}
