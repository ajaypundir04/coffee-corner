package com.charlene.model;

import com.charlene.entity.Extra;
import com.charlene.entity.Offering;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @author Ajay Singh Pundir
 * Use to handle the order.
 */
public class Order {
    private List<Offering> offerings;
    private List<Extra> extras;

    public Order(List<Offering> offerings, List<Extra> extras) {
        this.offerings = offerings;
        this.extras = extras;
    }

    public Order() {
        this.offerings = new LinkedList<>();
        this.extras = new LinkedList<>();
    }

    public List<Offering> getOfferings() {
        return offerings;
    }

    public void setOfferings(List<Offering> offerings) {
        this.offerings = offerings;
    }

    public List<Extra> getExtras() {
        return extras;
    }

    public void setExtras(List<Extra> extras) {
        this.extras = extras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(offerings, order.offerings) && Objects.equals(extras, order.extras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerings, extras);
    }

    @Override
    public String toString() {
        return "Order{" +
                "offerings=" + offerings +
                ", extras=" + extras +
                '}';
    }
}
