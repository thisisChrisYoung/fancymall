package com.chrisspace.fancymall.ware.dao;

import com.chrisspace.fancymall.ware.entity.WareInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 仓库信息
 * 
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-28 21:39:05
 */
@Mapper
public interface WareInfoDao extends BaseMapper<WareInfoEntity> {
	
}
