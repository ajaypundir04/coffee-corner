package com.charlene.integration;

import com.charlene.entity.Extra;
import com.charlene.entity.Offering;
import com.charlene.entity.Size;
import com.charlene.model.Invoice;
import com.charlene.service.CustomerBonus;
import com.charlene.service.OrderService;
import com.charlene.service.impl.CoffeeCornerService;
import com.charlene.service.impl.FreeBeverageBonus;
import com.charlene.service.impl.FreeExtraBonus;
import com.charlene.service.impl.OrderServiceImpl;
import com.charlene.util.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CoffeeCornerTest {

    private final OrderService orderService = new OrderServiceImpl();
    private final CustomerBonus customerBonus = new FreeBeverageBonus(new FreeExtraBonus());
    private final CoffeeCornerService coffeeCornerService = new CoffeeCornerService(orderService, customerBonus);

    @Test
    public void placeOrderTestWithFreeBeverage() {

        Invoice invoice = coffeeCornerService.placeOrder(new ArrayList<Offering>() {{
            add(TestUtil.createBeverageOffering());
            add(TestUtil.createBeverageWithNamePriceSizeAndQuantity("Coffee", 1.40, Size.medium, 4));
            add(TestUtil.createBeverageOffering());
        }}, new ArrayList<Extra>() {{
            add(TestUtil.createExtra());
        }});
        Assert.assertNotNull(invoice);
        Assert.assertEquals(6.60, invoice.getAmount(), 0.9);
        Assert.assertEquals(invoice.getOrder(), orderService.getOrder());

    }

    @Test
    public void placeOrderTestWithFreeExtras() {
        Invoice invoice = coffeeCornerService.placeOrder(new ArrayList<Offering>() {{
            add(TestUtil.createBeverageOffering());
            add(TestUtil.createSnackOffering());
        }}, new ArrayList<Extra>() {{
            add(TestUtil.createExtra());
        }});
        Assert.assertNotNull(invoice);
        Assert.assertEquals(5.58, invoice.getAmount(), 0.9);
        Assert.assertEquals(invoice.getOrder(), orderService.getOrder());

    }
}
