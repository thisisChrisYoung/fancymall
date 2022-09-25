package com.chrisspace.fancymall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chrisspace.fancymall.common.utils.PageUtils;
import com.chrisspace.fancymall.product.entity.SpuInfoDescEntity;

import java.util.Map;

/**
 * spu信息介绍
 *
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-25 22:55:16
 */
public interface SpuInfoDescService extends IService<SpuInfoDescEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

