package com.atguigu.bojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Integer orderId;//订单编号
    private String orderSequence;//订单序列号
    private String createTime;//创建事件
    private Integer totalCount;//总数量
    private Double totalAmount;//总金额
    private Integer orderStatus;//订单状态
    private Integer userId;//用户编号

}
