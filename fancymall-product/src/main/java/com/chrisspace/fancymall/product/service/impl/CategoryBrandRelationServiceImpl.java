package com.chrisspace.fancymall.product.service.impl;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chrisspace.fancymall.product.dao.BrandDao;
import com.chrisspace.fancymall.product.dao.CategoryDao;
import com.chrisspace.fancymall.product.entity.BrandEntity;
import com.chrisspace.fancymall.product.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chrisspace.fancymall.common.utils.PageUtils;
import com.chrisspace.fancymall.common.utils.Query;

import com.chrisspace.fancymall.product.dao.CategoryBrandRelationDao;
import com.chrisspace.fancymall.product.entity.CategoryBrandRelationEntity;
import com.chrisspace.fancymall.product.service.CategoryBrandRelationService;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements CategoryBrandRelationService {

    @Autowired
    BrandDao brandDao;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        // select brand_name, cat_name from t_relation where brand_id = params.get("brandId")
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List queryCatelogListPage(Map<String, Object> params) {
        Object brandId = params.get("brandId");
        // select brand_name, cat_name from t_relation where brand_id = params.get("brandId")
        QueryWrapper<CategoryBrandRelationEntity> wrapper = new QueryWrapper<>();
//        wrapper.select("catelog_id","catelog_name").eq("brand_id",brandId);
        wrapper.eq("brand_id",brandId);

        List<CategoryBrandRelationEntity> list = this.list(
                wrapper
        );

        return list;
    }

    @Override
    public void saveDetail(CategoryBrandRelationEntity categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long cateLogId = categoryBrandRelation.getCatelogId();

        BrandEntity brandEntity = brandDao.selectById(brandId);
        String brandName = brandEntity.getName();
        CategoryEntity categoryEntity = categoryDao.selectById(cateLogId);
        String cateLogName = categoryEntity.getName();

        categoryBrandRelation.setBrandName(brandName);
        categoryBrandRelation.setCatelogName(cateLogName);

        this.save(categoryBrandRelation);


    }

    @Override
    public void updateBrand(Long brandId, String name) {
        CategoryBrandRelationEntity entity = new CategoryBrandRelationEntity();
        entity.setBrandId(brandId);
        entity.setBrandName(name);
        UpdateWrapper<CategoryBrandRelationEntity> wrapper = new UpdateWrapper<>();
        wrapper.eq("brand_id",brandId);
        this.update(entity, wrapper);
    }

    @Override
    public void updateCategory(Long catId, String name) {

        this.baseMapper.updateCategory(catId, name);
    }

}