package com.charlene.service;

import com.charlene.model.Order;

/**
 * @author Ajay Singh Pundir
 * Use to handle the bonus management.
 */
public interface CustomerBonus {

    double evaluateBonus(Order order);
}
