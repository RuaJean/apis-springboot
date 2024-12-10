package com.xolit.e_commerce.ecommerce.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponseDTO {
    private Integer orderId;
    private LocalDateTime createdAt;
    private String status;
    private List<ItemResponse> items;

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<ItemResponse> getItems() {
        return items;
    }
    public void setItems(List<ItemResponse> items) {
        this.items = items;
    }

    public static class ItemResponse {
        private Integer productId;
        private Integer quantity;
        private Double price;

        public Integer getProductId() {
            return productId;
        }
        public void setProductId(Integer productId) {
            this.productId = productId;
        }
        public Integer getQuantity() {
            return quantity;
        }
        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
        public Double getPrice() {
            return price;
        }
        public void setPrice(Double price) {
            this.price = price;
        }
    }
}