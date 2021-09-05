package com.charlene.model;

import com.charlene.entity.Extra;
import com.charlene.entity.Offering;

import java.util.List;
import java.util.Objects;

public class Menu {

    private List<Offering> offerings;
    private List<Extra>  extras;

    public Menu(List<Offering> offerings, List<Extra> extras) {
        this.offerings = offerings;
        this.extras = extras;
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
        Menu menu = (Menu) o;
        return Objects.equals(offerings, menu.offerings) && Objects.equals(extras, menu.extras);
    }

    @Override
    public int hashCode() {
        return Objects.hash(offerings, extras);
    }
}
