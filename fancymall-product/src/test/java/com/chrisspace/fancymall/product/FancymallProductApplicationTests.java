package com.chrisspace.fancymall.product;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chrisspace.fancymall.product.entity.BrandEntity;
import com.chrisspace.fancymall.product.service.BrandService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@MapperScan("com.chrisspace.fancymall.product.dao")
@SpringBootTest
class FancymallProductApplicationTests {

	@Autowired
	BrandService brandService;
	@Test
	void contextLoads() {
		BrandEntity brandEntity = new BrandEntity();
//
//		brandEntity.setDescript("test");
//		brandEntity.setName("apple");
//		brandService.save(brandEntity);
//		System.out.println("success");

//		brandEntity.setBrandId(1L);
//		brandEntity.setName("AMAZON");
//		brandService.updateById(brandEntity);
//		System.out.println("SUCCESS");

		List<BrandEntity> list = brandService.list(new QueryWrapper<BrandEntity>().eq("brand_id", 1L));
		list.forEach(System.out::println);
	}

}
