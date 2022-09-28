package com.chrisspace.fancymall.member.dao;

import com.chrisspace.fancymall.member.entity.MemberLevelEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员等级
 * 
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-28 21:23:10
 */
@Mapper
public interface MemberLevelDao extends BaseMapper<MemberLevelEntity> {
	
}