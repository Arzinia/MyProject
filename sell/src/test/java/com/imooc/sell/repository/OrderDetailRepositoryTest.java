package com.imooc.sell.repository;

import com.imooc.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1102");
        orderDetail.setOrderId("110");
        orderDetail.setProductId("123456");
        orderDetail.setProductName("芋圆四号");
        orderDetail.setProductPrice(new BigDecimal(24.3));
        orderDetail.setProductQuantity(3);
        orderDetail.setProductIcon("http://xxxxxx.jpg");
        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByOrderId() throws Exception{
        List<OrderDetail> orderDetailList = repository.findByOrderId("110");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}