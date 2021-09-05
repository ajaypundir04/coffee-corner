package com.charlene.entity;

import java.util.Objects;

public class Offering extends Item {

    private Size size;
    private String type;

    public Offering(String name, String type, Size size,
                    int quantity, double price
    ) {
        super(name, price, quantity);
        this.size = size;
        this.type = type;

    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offering offering = (Offering) o;
        return size == offering.size
                && type == offering.type
                && super.getName() == offering.getName()
                && super.getPrice() == offering.getPrice()
                && super.getQuantity() == offering.getQuantity();
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, type,
                super.getName(), super.getName(), super.getQuantity());
    }

    @Override
    public String toString() {
        return "Offering{" +
                "size=" + size +
                ", type='" + type + '\'' +
                super.toString() +
                '}';
    }
}
