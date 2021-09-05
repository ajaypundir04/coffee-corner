package com.charlene.service;

import com.charlene.entity.Extra;
import com.charlene.entity.Offering;
import com.charlene.model.Order;
import com.charlene.service.impl.OrderServiceImpl;
import com.charlene.util.TestUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class OrderServiceTest {

    private OrderService orderService;

    @Before
    public void setUp() {
        orderService = new OrderServiceImpl();
    }

    @Test
    public void addOrUpdateOfferingsTest() {
        Offering offering = TestUtil.createBeverageOffering();
        Order actualOrder = orderService.addOrUpdateOfferings(offering, 2);
        offering.setQuantity(2);
        offering.setPrice(offering.getPrice() * 2);
        Assert.assertEquals(new ArrayList<Offering>() {
            {
                add(offering);
            }
        }, actualOrder.getOfferings());
    }

    @Test
    public void addOrUpdateExtrasTest() {
        Extra extra = TestUtil.createExtra();
        Order actualOrder = orderService.addOrUpdateExtras(extra, 2);
        extra.setQuantity(2);
        extra.setPrice(extra.getPrice() * 2);
        Assert.assertEquals(new ArrayList<Extra>() {
            {
                add(extra);
            }
        }, actualOrder.getExtras());
    }

    @Test
    public void cancelOrderTest() {
        orderService.cancelOrder(orderService.getOrder());
        Assert.assertNull(orderService.getOrder());
    }

    @Test
    public void getOrderTest() {
        Assert.assertNotNull(orderService.getOrder());
    }


}
