package com.chrisspace.fancymall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chrisspace.fancymall.product.entity.CategoryEntity;
import com.chrisspace.fancymall.product.service.CategoryService;
import com.chrisspace.fancymall.common.utils.PageUtils;
import com.chrisspace.fancymall.common.utils.R;



/**
 * 商品三级分类
 *
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-25 22:55:16
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查出所有的分类和子分类，以树形结构组装
     */
    @RequestMapping("/list/tree")
    public R list(){

        List<CategoryEntity> entities = categoryService.listWithTree();
        return R.ok().put("data", entities);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
   // //@RequiresPermissions("product:category:info")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("data", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // //@RequiresPermissions("product:category:save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改  级联更新
     */
    @RequestMapping("/update")
    // //@RequiresPermissions("product:category:update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateCasacade(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update/sort")
    // //@RequiresPermissions("product:category:update")
    public R update(@RequestBody CategoryEntity[] category){
        categoryService.updateBatchById(Arrays.asList(category));

        return R.ok();
    }

    /**
     * 删除
     * @RequestBody MVC自动将请求体json转为对象 eg：long[]
     */
    @RequestMapping("/delete")
    // //@RequiresPermissions("product:category:delete")
    public R delete(@RequestBody Long[] catIds){
//		categoryService.removeByIds(Arrays.asList(catIds));
        categoryService.removeMenuByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
