package com.atguigu.bojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    private Integer itemId;//订单项编号
    private String bookName;//图书名称
    private Double price;//图书单价
    private String imgPath;//图书图片
    private Integer itemCount;//订单项数量
    private Double itemAmount;//订单项小计
    private Integer orderId;//订单编号

}
