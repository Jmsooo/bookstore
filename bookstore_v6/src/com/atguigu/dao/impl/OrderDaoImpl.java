package com.atguigu.dao.impl;

import com.atguigu.base.BaseDao;
import com.atguigu.bojo.Order;
import com.atguigu.dao.OrderDao;

public class OrderDaoImpl extends BaseDao<OrderDao> implements OrderDao {
    @Override
    public void addOrder(Order order) {

        String sql = "insert into t_order values(null ,?,?,?,?,?,?)";
        Object[] params = {
                order.getOrderSequence(),
                order.getCreateTime(),
                order.getTotalCount(),
                order.getTotalAmount(),
                order.getOrderStatus(),
                order.getUserId()
        };

        Integer generatedKey = insertGeneratedKey(sql, params);
        System.out.println("generatedKey = " + generatedKey);
        order.setOrderId(generatedKey);
    }
}
