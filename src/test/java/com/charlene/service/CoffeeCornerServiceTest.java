package com.charlene.service;

import com.charlene.entity.Offering;
import com.charlene.model.Invoice;
import com.charlene.model.Order;
import com.charlene.service.impl.CoffeeCornerService;
import com.charlene.service.impl.FreeBeverageBonus;
import com.charlene.service.impl.OrderServiceImpl;
import com.charlene.util.TestUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;

public class CoffeeCornerServiceTest {

    private final OrderService orderService = Mockito.mock(OrderServiceImpl.class);
    private final CustomerBonus customerBonus = Mockito.mock(FreeBeverageBonus.class);
    private final CoffeeCornerService coffeeCornerService = new CoffeeCornerService(orderService, customerBonus);
    private final Order order = new Order(new ArrayList<Offering>() {{
        add(TestUtil.createBeverageOffering());
    }}, null);

    @Before
    public void setUp() {
        Mockito.reset(orderService);
        Mockito.reset(customerBonus);
    }

    @Test
    public void placeOrderTest() {
        Mockito.when(orderService.addOrUpdateOfferings(Mockito.any(Offering.class), Mockito.anyInt())).thenReturn(order);
        Mockito.when(orderService.getOrder()).thenReturn(order);
        Mockito.when(customerBonus.evaluateBonus(Mockito.any(Order.class))).thenReturn(0.0);
        Invoice invoice = coffeeCornerService.placeOrder(order.getOfferings(), order.getExtras());
        Assert.assertNotNull(invoice);
        Assert.assertEquals(4.40, invoice.getAmount(), 0.9);
        Assert.assertEquals(invoice.getOrder(), order);
        InOrder inOrder = Mockito.inOrder(orderService);
        inOrder.verify(orderService, Mockito.times(1)).addOrUpdateOfferings(Mockito.any(Offering.class), Mockito.anyInt());
        inOrder.verify(orderService, Mockito.times(2)).getOrder();
        inOrder = Mockito.inOrder(customerBonus);
        inOrder.verify(customerBonus, Mockito.times(1)).evaluateBonus(Mockito.any(Order.class));
    }
}
