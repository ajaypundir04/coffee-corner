package com.charlene.service;

import com.charlene.entity.Size;
import com.charlene.service.impl.FreeBeverageBonus;
import com.charlene.service.impl.FreeExtraBonus;
import com.charlene.service.impl.OrderServiceImpl;
import com.charlene.util.TestUtil;
import org.junit.Assert;
import org.junit.Test;

public class CustomerBonusTest {

    private final CustomerBonus bonus = new FreeBeverageBonus(new FreeExtraBonus());
    private final OrderService orderService = new OrderServiceImpl();

    @Test
    public void freeBeverageTest() {
        orderService.addOrUpdateOfferings(TestUtil.createBeverageWithNamePriceAndSize("Latte Coffee", 5.40, Size.large), 5);
        orderService.addOrUpdateOfferings(TestUtil.createBeverageWithNamePriceAndSize("Latte Coffee", 3.40, Size.medium), 2);
        orderService.addOrUpdateOfferings(TestUtil.createBeverageWithNamePriceAndSize("Latte Coffee", 1.40, Size.small), 3);
        Assert.assertEquals(6.8, bonus.evaluateBonus(orderService.getOrder()), 0.9);
    }

    @Test
    public void freeSnacksTest() {
        orderService.addOrUpdateOfferings(TestUtil.createBeverageWithNamePriceAndSize("Latte Coffee", 5.40, Size.large), 4);
        orderService.addOrUpdateOfferings(TestUtil.createSnackOffering(), 1);
        orderService.addOrUpdateExtras(TestUtil.createExtra(), 1);
        Assert.assertEquals(2.20, bonus.evaluateBonus(orderService.getOrder()), 0.9);
    }

    @Test
    public void noBonusTest() {
        orderService.addOrUpdateOfferings(TestUtil.createBeverageWithNamePriceAndSize("Latte Coffee", 5.40, Size.large), 4);
        Assert.assertEquals(0, bonus.evaluateBonus(orderService.getOrder()), 0.9);
    }

}
