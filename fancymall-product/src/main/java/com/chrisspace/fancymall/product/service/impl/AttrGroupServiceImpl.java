package com.chrisspace.fancymall.product.service.impl;

import com.alibaba.nacos.shaded.io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import com.aliyuncs.utils.StringUtils;
import com.chrisspace.fancymall.common.utils.PageUtils;
import com.chrisspace.fancymall.common.utils.Query;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.chrisspace.fancymall.product.dao.AttrGroupDao;
import com.chrisspace.fancymall.product.entity.AttrGroupEntity;
import com.chrisspace.fancymall.product.service.AttrGroupService;


@Service("attrGroupService")
public class AttrGroupServiceImpl extends ServiceImpl<AttrGroupDao, AttrGroupEntity> implements AttrGroupService {

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
    public PageUtils queryPage(Map<String, Object> params, Long catelogId) {
        if (catelogId == 0){
            IPage<AttrGroupEntity> page = this.page(
                    // 哪个mapper
                    new Query<AttrGroupEntity>().getPage(params),
                    // 查询条件
                    new QueryWrapper<AttrGroupEntity>()
            );
            return new PageUtils(page);
        }else {
            String key = (String) params.get("key");
            // wrapper: 查询条件  需要构造  where 'catelog_id' = catelogId and ('name' like %key% or 'id' = key)
            QueryWrapper<AttrGroupEntity> wrapper = new QueryWrapper<AttrGroupEntity>().eq("catelog_id",catelogId);
            if (StringUtils.isEmpty(key)){
                wrapper.and((obj) ->{
                    obj.eq("attr_group_id",key).or().like("attr_group_name",key);
                });
            }

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