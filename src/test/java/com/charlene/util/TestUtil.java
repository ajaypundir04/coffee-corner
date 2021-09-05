package com.charlene.util;

import com.charlene.constants.ApplicationConstants;
import com.charlene.entity.Extra;
import com.charlene.entity.Offering;
import com.charlene.entity.Size;
import com.charlene.model.Order;

import java.util.ArrayList;

public class TestUtil {

    private TestUtil() {

    }

    public static Offering createBeverageOffering() {
        return createBeverageWithNamePriceAndSize("Coffee", 4.40, Size.medium);

    }

    public static Offering createBeverageWithNamePriceAndSize(String name, double price, Size size) {
        return createBeverageWithNamePriceSizeAndQuantity(name, price, size, 1);

    }

    public static Offering createBeverageWithNamePriceSizeAndQuantity(String name,
                                                                      double price,
                                                                      Size size, int quantity) {
        return new Offering(name, ApplicationConstants.BEVERAGE,
                size, quantity, price);

    }

    public static Offering createSnackOffering() {
        return new Offering("Bacon Roll", ApplicationConstants.SNACK,
                Size.not_applicable, 1, 1.40);

    }

    public static Extra createExtra() {
        return new Extra("Extra Coffee", 1, 2.20);
    }

    public static Order createOrder() {
        return new Order(new ArrayList<Offering>() {{
            add(TestUtil.createBeverageOffering());
        }}, new ArrayList<Extra>() {{
            add(TestUtil.createExtra());
        }});
    }
}
