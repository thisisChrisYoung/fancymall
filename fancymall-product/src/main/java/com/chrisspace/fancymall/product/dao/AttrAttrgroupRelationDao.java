package com.chrisspace.fancymall.product.dao;

import com.chrisspace.fancymall.product.entity.AttrAttrgroupRelationEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 属性&属性分组关联
 * 
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-25 22:55:16
 */
@Mapper
public interface AttrAttrgroupRelationDao extends BaseMapper<AttrAttrgroupRelationEntity> {

    Long queryAttrGroupIdByAttrId(@Param("attrId") Long attrId);

    void updateGroupIdByAttrId(@Param("attrId") Long attrId, @Param("attrGroupId") Long attrGroupId);

    int countGroupId(@Param("attrId")Long attrId);

    List<Long> queryAttrIdsByGroupId(@Param("attrGroupId") Long attrGroupId);

    int deleteByAttrIdAndGroupId(@Param("entities") List<AttrAttrgroupRelationEntity> list);

    List<Long> queryAttrsNotRelateByGroupId(@Param("catelogId")Long catelogId, @Param("attrGroupIds") List<Long> attrGroupIds);
}
