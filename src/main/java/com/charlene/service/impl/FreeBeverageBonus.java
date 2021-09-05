package com.charlene.service.impl;

import com.charlene.constants.ApplicationConstants;
import com.charlene.entity.Item;
import com.charlene.entity.Offering;
import com.charlene.model.Order;
import com.charlene.service.CustomerBonus;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ajay Singh Pundir
 * Use to evaluate the free beverage bonus.
 */
public class FreeBeverageBonus implements CustomerBonus {

    private final CustomerBonus nextBonusProcessor;

    public FreeBeverageBonus(CustomerBonus nextBonusProcessor) {
        this.nextBonusProcessor = nextBonusProcessor;
    }

    /**
     * Evaluate if the order is eligible for free beverage.
     *
     * @param order @{@link Order} order needs to be evaluated
     * @return @{@link Double} discount
     */
    @Override
    public double evaluateBonus(Order order) {
        double discount = 0;
        List<Offering> bOfferings = order.getOfferings().stream()
                .filter(o -> o.getType().equals(ApplicationConstants.BEVERAGE))
                .collect(Collectors.toList());
        int bCount = bOfferings.stream()
                .mapToInt(Item::getQuantity)
                .sum();

        if (bCount >= 5) {
            int minBCount = 5;
            for (int i = 0; i < bOfferings.size(); i++) {
                Item it = bOfferings.get(i);
                minBCount -= it.getQuantity();
                if (minBCount <= 0) {
                    discount += it.getPrice() / it.getQuantity();
                    minBCount = 5;
                }

            }
            return discount;
        }

        return nextBonusProcessor.evaluateBonus(order);
    }
}
