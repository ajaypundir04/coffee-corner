package com.charlene.entity;

public class Extra extends Item {

    public Extra(String name, int quantity, double price) {
        super(name, price, quantity);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Extra{} " + super.toString();
    }
}
