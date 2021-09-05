package com.charlene.service.impl;

import com.charlene.entity.Extra;
import com.charlene.entity.Offering;
import com.charlene.model.Order;
import com.charlene.service.OrderService;

import java.util.Objects;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private Order order;

    public OrderServiceImpl() {
        this.order = new Order();
    }

    @Override
    public Order addOrUpdateOfferings(Offering item, int quantity) {
        item.setQuantity(quantity);
        item.setPrice(item.getPrice() * quantity);
        order.setOfferings(order.getOfferings()
                .stream()
                .filter(Objects::nonNull)
                .filter(i -> !(i.getName().equalsIgnoreCase(item.getName()) && (item.getSize() == i.getSize())))
                .collect(Collectors.toList()));

        if (item.getQuantity() > 0) order.getOfferings().add(item);
        return this.order;
    }

    @Override
    public Order addOrUpdateExtras(Extra item, int quantity) {
        item.setQuantity(quantity);
        item.setPrice(item.getPrice() * quantity);
        order.setExtras(order.getExtras()
                .stream()
                .filter(Objects::nonNull)
                .filter(i -> !(i.getName().equalsIgnoreCase(item.getName())))
                .collect(Collectors.toList()));
        if (item.getQuantity() > 0) order.getExtras().add(item);
        return this.order;
    }

    @Override
    public void cancelOrder(Order order) {
        this.order = null;
    }

    public Order getOrder() {
        return this.order;
    }
}
