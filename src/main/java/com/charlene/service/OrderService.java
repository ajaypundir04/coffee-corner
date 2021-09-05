package com.charlene.service;

import com.charlene.entity.Extra;
import com.charlene.entity.Offering;
import com.charlene.model.Order;

/**
 * @author Ajay Singh Pundir
 * Use to handle the Order Management.
 */
public interface OrderService {
    Order addOrUpdateOfferings(Offering item, int quantity);

    Order addOrUpdateExtras(Extra item, int quantity);

    void cancelOrder(Order order);

    Order getOrder();
}
