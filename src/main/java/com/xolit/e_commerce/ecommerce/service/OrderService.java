package com.xolit.e_commerce.ecommerce.service;

import com.xolit.e_commerce.ecommerce.dto.OrderRequestDTO;
import com.xolit.e_commerce.ecommerce.dto.OrderResponseDTO;
import com.xolit.e_commerce.ecommerce.entity.Order;
import com.xolit.e_commerce.ecommerce.entity.OrderItem;
import com.xolit.e_commerce.ecommerce.entity.Product;
import com.xolit.e_commerce.ecommerce.observer.publisher.OrderPublisher;
import com.xolit.e_commerce.ecommerce.repository.OrderRepository;
import com.xolit.e_commerce.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderPublisher orderPublisher;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        OrderPublisher orderPublisher) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.orderPublisher = orderPublisher;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO request) {
        final Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus("CREATED");

        var items = request.getItems().stream().map(reqItem -> {
            Product product = productRepository.findById(reqItem.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setPrice(reqItem.getPrice());
            item.setQuantity(reqItem.getQuantity());
            return item;
        }).collect(Collectors.toList());

        order.setItems(items);
        Order savedOrder = orderRepository.save(order);

        // Notificar a suscriptores (Observer)
        orderPublisher.notifySubscribers(savedOrder);

        // Preparar respuesta
        OrderResponseDTO response = new OrderResponseDTO();
        response.setOrderId(savedOrder.getId());
        response.setCreatedAt(savedOrder.getCreatedAt());
        response.setStatus(savedOrder.getStatus());
        response.setItems(
                savedOrder.getItems().stream().map(oi -> {
                    OrderResponseDTO.ItemResponse ir = new OrderResponseDTO.ItemResponse();
                    ir.setProductId(oi.getProduct().getId());
                    ir.setQuantity(oi.getQuantity());
                    ir.setPrice(oi.getPrice());
                    return ir;
                }).collect(Collectors.toList())
        );
        return response;
    }
}
