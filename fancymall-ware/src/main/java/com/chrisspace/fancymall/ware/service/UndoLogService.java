package com.chrisspace.fancymall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chrisspace.fancymall.common.utils.PageUtils;
import com.chrisspace.fancymall.ware.entity.UndoLogEntity;

import java.util.Map;

/**
 * 
 *
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-28 21:39:05
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

