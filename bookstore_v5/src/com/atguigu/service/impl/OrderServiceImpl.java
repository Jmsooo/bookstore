package com.atguigu.service.impl;

import com.atguigu.base.BaseConstant;
import com.atguigu.bojo.*;
import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDaoImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.service.OrderService;
import com.atguigu.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderServiceImpl implements OrderService {
    @Override
    public String checkout(Cart existCart, User loginUser) {
        Order order = null;
        Connection connection = null;
        try {
            //开启事务
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);

            //-----------------------------①生成订单-----------------------------
            OrderDao orderDao = new OrderDaoImpl();
            order = new Order();
            String sequenceStr = UUID.randomUUID().toString().replace("-", "");
            order.setOrderSequence(sequenceStr);
            order.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            order.setTotalCount(existCart.getTotalCount());
            order.setTotalAmount(existCart.getTotalAmount());
            order.setOrderStatus(BaseConstant.ORDER_UNCOMPLETED);
            order.setUserId(loginUser.getId());
            orderDao.addOrder(order);

            //-----------------------------②生成订单项-----------------------------
            OrderItemDao orderItemDao = new OrderItemDaoImpl();
            Integer orderId = order.getOrderId();
            Map<Integer, CartItem> cartItemMap = existCart.getCartItemMap();
            List<CartItem> cartItemList = new ArrayList<>(cartItemMap.values());
            Object[][] orderItemParams=  new Object[cartItemList.size()][6];
            for (int i = 0; i < cartItemList.size(); i++) {
                orderItemParams[i][0] = cartItemList.get(i).getBookName();
                orderItemParams[i][1] = cartItemList.get(i).getPrice();
                orderItemParams[i][2] = cartItemList.get(i).getImgPath();
                orderItemParams[i][3] = cartItemList.get(i).getCount();
                orderItemParams[i][4] = cartItemList.get(i).getAmount();
                orderItemParams[i][5] = orderId;
            }
            orderItemDao.addOrderItem(orderItemParams);

            //-----------------------------③修改库存和销量-----------------------------
            BookDao bookDao = new BookDaoImpl();
            Object[][] bookParams = new Object[cartItemList.size()][3];
            for (int i = 0; i < cartItemList.size(); i++) {
                bookParams[i][0] = cartItemList.get(i).getCount();
                bookParams[i][1] = cartItemList.get(i).getCount();
                bookParams[i][2] = cartItemList.get(i).getBookId();
            }

            bookDao.updateBooks(bookParams);

            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return order.getOrderSequence();
    }
}
