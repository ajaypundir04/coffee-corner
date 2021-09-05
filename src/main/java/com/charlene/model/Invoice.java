package com.charlene.model;

import java.util.Objects;

public class Invoice {
    private Order order;
    private double amount;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Double.compare(invoice.amount, amount) == 0 && Objects.equals(order, invoice.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, amount);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "order=" + order +
                ", amount=" + amount +
                '}';
    }
}
