package com.atguigu.dao.impl;

import com.atguigu.base.BaseDao;
import com.atguigu.bojo.OrderItem;
import com.atguigu.dao.OrderItemDao;

public class OrderItemDaoImpl extends BaseDao<OrderItem> implements OrderItemDao {
    @Override
    public void addOrderItem(Object[][] orderItemParams) {
        batch(
                "insert into t_order_item values(null,?,?,?,?,?,?)",
                orderItemParams
        );
    }
}
