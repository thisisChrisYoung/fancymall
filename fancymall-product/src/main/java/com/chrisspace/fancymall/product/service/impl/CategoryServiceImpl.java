package com.chrisspace.fancymall.product.service.impl;

import com.chrisspace.fancymall.common.utils.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chrisspace.fancymall.common.utils.PageUtils;

import com.chrisspace.fancymall.product.dao.CategoryDao;
import com.chrisspace.fancymall.product.entity.CategoryEntity;
import com.chrisspace.fancymall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 所有分类
        List<CategoryEntity> entities = baseMapper.selectList(null);

        // 组装树形list
        // 找出一级分类
        List<CategoryEntity> level1Menus = entities.stream().filter((item) -> item.getParentCid() == 0)
                .map((menu) -> {
                    menu.setChildren(getChildrens(menu, entities));
                    return menu;
                })
                .sorted((menu1, menu2) -> (menu1.getSort()==null ? 0 : menu1.getSort())  - (menu2.getSort()== null ? 0 : menu2.getSort()))
                .collect(Collectors.toList());


        return entities;
    }

    @Override
    public void removeMenuByIds(List<Long> catIds) {
        // TODO 检查当前删除的菜单是否被其他地方🚰

        // 给bean上加上逻辑删除注解
        baseMapper.deleteBatchIds(catIds);

    }

    // 递归查找当前菜单子菜单
    private List<CategoryEntity> getChildrens(CategoryEntity target, List<CategoryEntity> source){
        List<CategoryEntity> children = source.stream().filter(menu -> menu.getParentCid() == target.getCatId())
                .map((menu) -> {
                    // 找2级分类子菜单
                    menu.setChildren(getChildrens(menu, source));
                    return menu;
                })
                // 排序
                .sorted((menu1, menu2) -> (menu1.getSort()==null ? 0 : menu1.getSort())  - (menu2.getSort()== null ? 0 : menu2.getSort()))
                .collect(Collectors.toList());
        ;
        return children;
    }

}