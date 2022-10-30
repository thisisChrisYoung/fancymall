package com.chrisspace.fancymall.product.service.impl;

import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import com.aliyuncs.utils.StringUtils;
import com.chrisspace.fancymall.common.utils.PageUtils;
import com.chrisspace.fancymall.common.utils.Query;
import com.chrisspace.fancymall.common.utils.R;
import com.chrisspace.fancymall.product.dao.AttrAttrgroupRelationDao;
import com.chrisspace.fancymall.product.dao.AttrDao;
import com.chrisspace.fancymall.product.entity.AttrEntity;
import com.chrisspace.fancymall.product.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.chrisspace.fancymall.product.dao.AttrGroupDao;
import com.chrisspace.fancymall.product.entity.AttrGroupEntity;
import com.chrisspace.fancymall.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

    @Autowired
    AttrAttrgroupRelationDao relationDao;
    @Autowired
    AttrDao attrDao;
    @Autowired
    AttrGroupDao attrGroupDao;
    @Autowired
    AttrService attrService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<AttrGroupEntity> page = this.page(
                // 哪个mapper
                new Query<AttrGroupEntity>().getPage(params),
                // 查询条件
                new QueryWrapper<AttrGroupEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryAttrsNotRelateByGroupId(Long attrGroupId, Map<String, Object> params) {

        // 当前分类下的属性  剔除其他分组用过的
        AttrGroupEntity attrGroupEntity = attrGroupDao.selectById(attrGroupId);
        Long catelogId = attrGroupEntity.getCatelogId();

        // 找到分类下的所有分组
        List<AttrGroupEntity> groupEntities = attrGroupDao.selectByCateLogId(catelogId);
        // 过滤出其他分组对象取id收集成list
        List<Long> otherGroupList = groupEntities.stream().map(AttrGroupEntity::getAttrGroupId).filter(groupId -> groupId != attrGroupId).collect(Collectors.toList());

        // 找到当前分类下其他分组没用过的attr
        List<Long> attrNotRelateIds = relationDao.queryAttrsNotRelateByGroupId(catelogId,otherGroupList);

        QueryWrapper<AttrEntity> wrapper = new QueryWrapper<AttrEntity>().eq("catelog_id",catelogId).in("attr_id",attrNotRelateIds);

        String key = (String) params.get("key");
        if (StringUtils.isEmpty(key)){
            wrapper.and((w)->{
                w.eq("attr_id", key).or().like("attr_name",key);
            });
        }

        IPage<AttrEntity> page = attrService.page(new Query<AttrEntity>().getPage(params), wrapper);

        return new PageUtils(page);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        String key = (String) params.get("key");
        // wrapper: 查询条件  需要构造  where 'catelog_id' = catelogId and ('name' like %key% or 'id' = key)
        QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>();
        if (StringUtils.isEmpty(key)){
            wrapper.and((obj) ->{
                obj.eq("attr_group_id",key).or().like("attr_group_name",key);
            });
        }

        if (catelogId == 0){
            IPage<AttrGroupEntity> page = this.page(
                    // 哪个mapper
                    new Query<AttrGroupEntity>().getPage(params),
                    // 查询条件
                    new QueryWrapper<AttrGroupEntity>()
            );
            return new PageUtils(page);
        }else {

            wrapper.eq("catelog_id",catelogId);
            IPage<AttrGroupEntity> page = this.page(
                    // 哪个mapper
                    new Query<AttrGroupEntity>().getPage(params),
                    // 查询条件
                    wrapper
            );

            return new PageUtils(page);


        }
    }
}