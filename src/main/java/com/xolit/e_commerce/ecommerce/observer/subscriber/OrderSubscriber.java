package com.xolit.e_commerce.ecommerce.observer.subscriber;

import com.xolit.e_commerce.ecommerce.entity.Order;

public interface OrderSubscriber {
    void update(Order order);
}