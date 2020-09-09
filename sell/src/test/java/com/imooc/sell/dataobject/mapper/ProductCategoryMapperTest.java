package com.imooc.sell.dataobject.mapper;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.imooc.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ProductCategoryMapperTest {
    @Autowired
    private ProductCategoryMapper mapper;
    @Test
    public void insertByMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("category_name","夏季缤纷");
        map.put("category_type",311);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }
    @Test
    public void insertByObject() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(233);
        productCategory.setCategoryName("咖啡时光");
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
    }
    @Test
    public void find(){
        ProductCategory productCategory = mapper.findByCategoryType(233);
        Assert.assertNotNull(productCategory);
    }
    @Test
    public void update(){
        int result = mapper.updateByCategoryType("美味冰淇淋",233);
        Assert.assertEquals(1 ,result);
    }
    @Test
    public void updateByObject(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(233);
        productCategory.setCategoryName("咖啡时光");
        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(1,result);
    }
    @Test
    public void select(){
        ProductCategory productCategory = mapper.selectByCategoryType(233);
        Assert.assertNotNull(productCategory);
    }
}