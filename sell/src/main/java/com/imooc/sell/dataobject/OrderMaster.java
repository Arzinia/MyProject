package com.imooc.sell.dataobject;

import com.imooc.sell.enums.OrderStatusEnum;
import com.imooc.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
//    private Integer orderStatus;
//    订单状态，默认0为新下单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
//    支付状态，默认0为未支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();
//    private Integer payStatus;
    private Date createTime;
    private Date updateTime;
}
