package com.chrisspace.fancymall.member.dao;

import com.chrisspace.fancymall.member.entity.MemberCollectSpuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员收藏的商品
 * 
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-28 21:23:10
 */
@Mapper
public interface MemberCollectSpuDao extends BaseMapper<MemberCollectSpuEntity> {
	
}
