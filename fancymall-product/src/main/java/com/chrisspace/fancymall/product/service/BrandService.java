package com.chrisspace.fancymall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chrisspace.fancymall.common.utils.PageUtils;
import com.chrisspace.fancymall.product.entity.BrandEntity;

import java.util.Map;

/**
 * 品牌
 *
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-25 22:55:16
 */
public interface BrandService extends IService<BrandEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

