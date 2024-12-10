package com.xolit.e_commerce.ecommerce.observer.publisher;

import com.xolit.e_commerce.ecommerce.entity.Order;
import com.xolit.e_commerce.ecommerce.observer.subscriber.OrderSubscriber;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Sujeto (Publisher) que notifica a los suscriptores.
 */
@Component
public class OrderPublisher {
    private final List<OrderSubscriber> subscribers = new ArrayList<>();

    public void addSubscriber(OrderSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(OrderSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers(Order order) {
        for (OrderSubscriber sub : subscribers) {
            sub.update(order);
        }
    }
}