package com.chrisspace.fancymall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chrisspace.fancymall.common.utils.PageUtils;
import com.chrisspace.fancymall.product.entity.AttrEntity;
import com.chrisspace.fancymall.product.vo.AttrRespVo;
import com.chrisspace.fancymall.product.vo.AttrVo;

import java.util.Map;

/**
 * 商品属性
 *
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-25 22:55:16
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);

    PageUtils queryBaseAttrPage(Map<String, Object> param, Long catelogId, String attrType);

    AttrRespVo getFullInfoById(Long attrId);

    void updateAttr(AttrVo attr);
}

