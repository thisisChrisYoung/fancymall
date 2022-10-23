package com.chrisspace.fancymall.product.service.impl;

import com.chrisspace.fancymall.common.utils.PageUtils;
import com.chrisspace.fancymall.product.dao.CategoryBrandRelationDao;
import com.chrisspace.fancymall.product.service.CategoryBrandRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chrisspace.fancymall.common.utils.PageUtils;
import com.chrisspace.fancymall.common.utils.Query;

import com.chrisspace.fancymall.product.dao.BrandDao;
import com.chrisspace.fancymall.product.entity.BrandEntity;
import com.chrisspace.fancymall.product.service.BrandService;
import org.springframework.transaction.annotation.Transactional;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {

        String key = (String) params.get("key");

        QueryWrapper<BrandEntity> wrapper = new QueryWrapper<>();
        //select * from t where  brand_name like #{key}% or id = id
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),

                wrapper.eq("brand_id",key).or().like("name",key)
        );

        return new PageUtils(page);
    }

    @Transactional //一个方法内多个数据库操作 加上事务
    @Override
    public void updateDetail(BrandEntity brand) {
        this.updateById(brand);

        if (StringUtils.isNotEmpty(brand.getName())){
            categoryBrandRelationService.updateBrand(brand.getBrandId(), brand.getName());

            //TODO 更新其他关联表维护冗余字段
        }
    }

}