package com.atguigu.bojo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private Integer totalCount;
    private Double totalAmount;
    private Map<Integer, CartItem> cartItemMap = new HashMap<Integer, CartItem>();

    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    /**
     * 获取商品总数量 : 购物车项数量之和
     *
     * @return
     */
    public Integer getTotalCount() {
        Collection<CartItem> cartItems = cartItemMap.values();
        Integer totalCount = 0;
        for (CartItem cartItem : cartItems) {
            totalCount += cartItem.getCount();
        }
        this.totalCount = totalCount;
        return this.totalCount;
    }

    /**
     * 获取商品总金额 : 购物车项小计之和
     *
     * @return
     */
    public Double getTotalAmount() {
        Collection<CartItem> cartItems = cartItemMap.values();
        Double totalAmount = 0.0;
        for (CartItem cartItem : cartItems) {
            totalAmount += cartItem.getAmount();
        }
        this.totalAmount = totalAmount;
        return this.totalAmount;
    }

    /**
     * 指定购物车项数量减一
     *
     * @param bookId
     */
    public void cartItemCountDecrease(Integer bookId) {
        CartItem cartItem = cartItemMap.get(bookId);
        cartItem.countDecrease();
    }

    /**
     * 指定购物车项数量加一
     *
     * @param bookId
     */
    public void cartItemCountIncrease(Integer bookId) {
        CartItem cartItem = cartItemMap.get(bookId);
        cartItem.countIncrease();
    }

    /**
     * 修改指定购物车项数量
     *
     * @param bookId
     * @param newCount
     */
    public void updateCartItemCount(Integer bookId, Integer newCount) {
        CartItem cartItem = cartItemMap.get(bookId);
        cartItem.setCount(newCount);
    }

    /**
     * 移除指定购物车项
     *
     * @param bookId
     */
    public void removeCartItem(Integer bookId) {
        cartItemMap.remove(bookId);
    }

    /**
     * 将图书添加到购物车
     *
     * @param book
     */
    public void addBookToCart(Book book) {
        int bookId = book.getBookId();
        if (cartItemMap.containsKey(bookId)) {
            cartItemCountIncrease(bookId);
        } else {
            CartItem cartItem = new CartItem(
                    bookId,
                    book.getImgPath(),
                    book.getBookName(),
                    1,
                    book.getPrice(),
                    1 * book.getPrice()
            );
            cartItemMap.put(bookId, cartItem);
        }
    }

}
