package com.chrisspace.fancymall.product.service.impl;

import com.alibaba.nacos.shaded.org.checkerframework.checker.units.qual.A;
import com.chrisspace.fancymall.product.dao.AttrAttrgroupRelationDao;
import com.chrisspace.fancymall.product.dao.AttrGroupDao;
import com.chrisspace.fancymall.product.dao.CategoryDao;
import com.chrisspace.fancymall.product.entity.AttrAttrgroupRelationEntity;
import com.chrisspace.fancymall.product.entity.AttrGroupEntity;
import com.chrisspace.fancymall.product.entity.CategoryEntity;
import com.chrisspace.fancymall.product.vo.AttrRespVo;
import com.chrisspace.fancymall.product.vo.AttrVo;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chrisspace.fancymall.common.utils.PageUtils;
import com.chrisspace.fancymall.common.utils.Query;

import com.chrisspace.fancymall.product.dao.AttrDao;
import com.chrisspace.fancymall.product.entity.AttrEntity;
import com.chrisspace.fancymall.product.service.AttrService;
import org.springframework.transaction.annotation.Transactional;


@Service("attrService")
public class AttrServiceImpl extends ServiceImpl<AttrDao, AttrEntity> implements AttrService {

    @Autowired
    AttrAttrgroupRelationDao relationDao;

    @Autowired
    AttrGroupDao attrGroupDao;

    @Autowired
    AttrDao attrDao;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryServiceImpl categoryService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(params),
                new QueryWrapper<AttrEntity>()
        );

        return new PageUtils(page);
    }

    @Transactional
    @Override
    public void saveAttr(AttrVo attr) {
        AttrEntity attrEntity = new AttrEntity();
        // vo -> po  属性名一一对应
        BeanUtils.copyProperties(attr, attrEntity);
        // 保存基本数据
        this.save(attrEntity);
        // 保存关联关系
        AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
        relationEntity.setAttrGroupId(attr.getAttrGroupId());
        relationEntity.setAttrId(attrEntity.getAttrId());
        relationDao.insert(relationEntity);

    }

    @Override
    public PageUtils queryBaseAttrPage(Map<String, Object> param, Long catelogId) {

        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<>();

//        attrGroupDao.selectById();

        if (catelogId != 0){
            wrapper.eq("catelog_id",catelogId);
        }

        String key = (String) param.get("key");

        if (StringUtils.isNotEmpty(key)){
            wrapper.and((queryWrapper) ->{
                queryWrapper.eq("attr_id",key).or().like("attr_name",key);
            });
        }

        IPage<AttrEntity> page = this.page(
                new Query<AttrEntity>().getPage(param),
                wrapper
        );

        PageUtils pageUtils = new PageUtils(page);

        List<AttrEntity> attrEntities = page.getRecords();

        List<AttrRespVo> respVos = attrEntities.stream().map((attr) -> {
            AttrRespVo attrRespVo = new AttrRespVo();

            BeanUtils.copyProperties(attr, attrRespVo);

            Long groupId = relationDao.queryAttrGroupIdByAttrId(attr.getAttrId());

            if (groupId != null){
                AttrGroupEntity group = attrGroupDao.selectById(groupId);
                String attrGroupName = group.getAttrGroupName();
                attrRespVo.setGroupName(attrGroupName);
            }

            Long cateId = attr.getCatelogId();
            CategoryEntity categoryEntity = categoryDao.selectById(cateId);

            if (categoryEntity != null){
                String categoryName = categoryEntity.getName();
                attrRespVo.setCatelogName(categoryName);
            }

            return attrRespVo;
        }).collect(Collectors.toList());

        // 复用分页参数  把list替换即可
        pageUtils.setList(respVos);

        return pageUtils;
    }

    @Override
    public AttrRespVo getFullInfoById(Long attrId) {

        AttrRespVo attrRespVo = new AttrRespVo();
        AttrEntity attrEntity = this.getById(attrId);
        BeanUtils.copyProperties(attrEntity, attrRespVo);

        // catePath   attrGroupId
        Long catelogId = attrRespVo.getCatelogId();
        Long[] cateLogPath = categoryService.findCateLogPath(catelogId);
        if (cateLogPath != null){
            attrRespVo.setCatelogPath(cateLogPath);
        }

        Long groupId = relationDao.queryAttrGroupIdByAttrId(attrId);
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(groupId);
        String attrGroupName = attrGroupEntity.getAttrGroupName();
        if (attrGroupName != null){
            attrRespVo.setGroupName(attrGroupName);
            attrRespVo.setAttrGroupId(groupId);

        }
//


        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);
        String categoryEntityName = categoryEntity.getName();

        if (categoryEntityName != null){
            attrRespVo.setCatelogName(categoryEntityName);
        }

        return attrRespVo;
    }

    @Override
    public void updateAttr(AttrVo attr) {
        // 基础信息修改
        AttrEntity attrEntity = new AttrEntity();
        BeanUtils.copyProperties(attr, attrEntity);
        this.updateById(attrEntity);

        int groupCount = relationDao.countGroupId(attr.getAttrId());
        if (groupCount > 0){
            // 附加信息修改
            relationDao.updateGroupIdByAttrId(attr.getAttrId(), attr.getAttrGroupId());
        }else {
            AttrAttrgroupRelationEntity relationEntity = new AttrAttrgroupRelationEntity();
            relationEntity.setAttrGroupId(attr.getAttrGroupId());
            relationEntity.setAttrId(attr.getAttrId());

            relationDao.insert(relationEntity);
        }



    }

}