package com.atguigu.service;

import com.atguigu.bojo.Cart;
import com.atguigu.bojo.User;

public interface OrderService {
    String checkout(Cart existCart, User loginUser);
}
