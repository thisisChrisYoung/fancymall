package com.chrisspace.fancymall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chrisspace.fancymall.common.utils.PageUtils;
import com.chrisspace.fancymall.product.entity.SkuImagesEntity;

import java.util.Map;

/**
 * sku图片
 *
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-25 22:55:16
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

