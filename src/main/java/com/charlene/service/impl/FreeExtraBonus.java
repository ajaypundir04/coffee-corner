package com.charlene.service.impl;

import com.charlene.constants.ApplicationConstants;
import com.charlene.entity.Extra;
import com.charlene.model.Order;
import com.charlene.service.CustomerBonus;

/**
 * @author Ajay Singh Pundir
 * Use to evaluate the free extra bonus.
 */
public class FreeExtraBonus implements CustomerBonus {

    /**
     * Evaluate if the order is eligible for free extras.
     *
     * @param order @{@link Order} order needs to be evaluated
     * @return @{@link Double} discount
     */
    @Override
    public double evaluateBonus(Order order) {
        long bCount = order.getOfferings().stream().filter(o -> o.getType().equals(ApplicationConstants.BEVERAGE)).count();
        long sCount = order.getOfferings().stream().filter(o -> o.getType().equals(ApplicationConstants.SNACK)).count();
        double discount = 0;
        if (bCount >= 1 && sCount >= 1) {
            discount = Double.MAX_VALUE;
            for (Extra e : order.getExtras()) {
                discount = Math.min(discount, e.getPrice());
            }
        }
        return discount;
    }
}
