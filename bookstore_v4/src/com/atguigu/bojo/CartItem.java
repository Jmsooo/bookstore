package com.atguigu.bojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    private Integer bookId;//图书编号
    private String imgPath;//图书图片
    private String bookName;//图书名称
    private Integer count;//商品数量
    private Double price;//图书单价
    private Double Amount;//商品小计

    /**
     * 购物车数量减一
     */
    public void countDecrease() {
        this.count--;
    }

    /**
     * 购物车数量加一
     */
    public void countIncrease() {
        this.count++;
    }

    /**
     * 计算商品小计
     */
    public Double getAmount() {
        BigDecimal countBig = new BigDecimal(this.count + "");
        BigDecimal priceBig = new BigDecimal(this.price + "");
        return countBig.multiply(priceBig).doubleValue();
    }
}
