package com.chrisspace.fancymall.product.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.chrisspace.fancymall.common.utils.PageUtils;
import com.chrisspace.fancymall.common.utils.R;
import com.chrisspace.fancymall.product.dao.AttrAttrgroupRelationDao;
import com.chrisspace.fancymall.product.dao.AttrDao;
import com.chrisspace.fancymall.product.entity.AttrAttrgroupRelationEntity;
import com.chrisspace.fancymall.product.entity.AttrEntity;
import com.chrisspace.fancymall.product.service.CategoryService;
import com.chrisspace.fancymall.product.vo.AttrRespVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chrisspace.fancymall.product.entity.AttrGroupEntity;
import com.chrisspace.fancymall.product.service.AttrGroupService;




/**
 * 属性分组
 *
 * @author chris
 * @email chris@gmail.com
 * @date 2022-09-25 22:55:16
 */
@RestController
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrAttrgroupRelationDao relationDao;

    @Autowired
    private AttrDao attrDao;

    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    // //@RequiresPermissions("product:attrgroup:list")
    public R list(@RequestParam Map<String, Object> params,@PathVariable Long catelogId){
        PageUtils page = attrGroupService.queryPage(params, catelogId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
   // //@RequiresPermissions("product:attrgroup:info")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

        // 增加返回路径字段
        Long catelogId = attrGroup.getCatelogId();
        Long[] path = categoryService.findCateLogPath(catelogId);
        attrGroup.setCatelogPath(path);

        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    // //@RequiresPermissions("product:attrgroup:save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    // //@RequiresPermissions("product:attrgroup:update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    // //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

    // /product/attrgroup/{attrgroupId}/attr/relation

    /**
     * 分组对应属性
     */
    @RequestMapping("/{attrgroupId}/attr/relation")
    public R queryAttrsByGroupId(@PathVariable("attrgroupId") Long attrGroupId){

        List<Long> attrIds = relationDao.queryAttrIdsByGroupId(attrGroupId);
        if (attrIds.size() > 0){
            List<AttrEntity> attrEntities = attrDao.queryAttrsByIds(attrIds);
            return R.ok().put("data",attrEntities);
        }else return R.ok().put("data",new ArrayList<>());

    }

    /**
     * 删除
     */
    @PostMapping("/attr/relation/delete")
    // //@RequiresPermissions("product:attrgroup:delete")
    public R delete(@RequestBody List<AttrRespVo> list){

        List<AttrAttrgroupRelationEntity> relationEntityList = list.stream().map(
                (item) -> {
                    AttrAttrgroupRelationEntity attrAttrgroupRelationEntity = new AttrAttrgroupRelationEntity();
                    BeanUtils.copyProperties(item, attrAttrgroupRelationEntity);
                    return attrAttrgroupRelationEntity;
                }
        ).collect(Collectors.toList());


        if (relationEntityList.size() > 0 ){

            int i = relationDao.deleteByAttrIdAndGroupId(relationEntityList);
            if (i > 0) return R.ok("success");
            else return R.error();
        }else
            return R.error();
    }

    // /product/attrgroup/attr/relation/delete


}
