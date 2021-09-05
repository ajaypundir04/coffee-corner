package com.charlene.service.impl;

import com.charlene.entity.Extra;
import com.charlene.entity.Offering;
import com.charlene.model.Order;
import com.charlene.service.OrderService;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Ajay Singh Pundir
 * Use to handle the order.
 */
public class OrderServiceImpl implements OrderService {

    private Order order;

    public OrderServiceImpl() {
        this.order = new Order();
    }

    /**
     * Add or Update the offering in the order.
     *
     * @param item     @{@link Offering} needs to updated
     * @param quantity number of offering
     * @return @{@link Order} updated order
     */
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

    /**
     * Add or Update the extra in the order.
     *
     * @param item     @{@link Extra} needs to updated
     * @param quantity number of extra
     * @return @{@link Order} updated order
     */
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

    /**
     * Cancel the current order.
     *
     * @param order @{@link Order} to be cancelled
     */
    @Override
    public void cancelOrder(Order order) {
        this.order = null;
    }

    /**
     * Returns the current order.
     *
     * @return @{@link Order} current order.
     */
    public Order getOrder() {
        return this.order;
    }
}
