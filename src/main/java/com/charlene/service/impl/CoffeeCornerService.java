package com.charlene.service.impl;

import com.charlene.entity.Extra;
import com.charlene.entity.Offering;
import com.charlene.model.Invoice;
import com.charlene.model.Order;
import com.charlene.service.CustomerBonus;
import com.charlene.service.OrderService;

import java.util.List;
import java.util.Objects;

/**
 * @author Ajay Singh Pundir
 * Use to handle all the coffee corner operations.
 */
public class CoffeeCornerService {

    private final OrderService orderService;
    private final CustomerBonus bonus;
    private final Invoice invoice;

    public CoffeeCornerService(OrderService orderService, CustomerBonus bonus) {
        this.orderService = orderService;
        this.bonus = bonus;
        this.invoice = new Invoice();
    }

    /**
     * Used to place the order and generate invoice.
     *
     * @param offerings @{@link Offering} list of offerings
     * @param extras    @{@link Extra} list of extras
     * @return @{@link Invoice} invoice generated
     */
    public Invoice placeOrder(List<Offering> offerings, List<Extra> extras) {
        generateInvoice(offerings, extras);
        return this.invoice;
    }

    private void generateInvoice(List<Offering> offerings, List<Extra> extras) {
        if (Objects.nonNull(offerings)) {
            offerings.stream().filter(Objects::nonNull).forEach(o -> orderService.addOrUpdateOfferings(o, o.getQuantity()));
        }
        if (Objects.nonNull(extras)) {
            extras.stream().filter(Objects::nonNull).forEach(e -> orderService.addOrUpdateExtras(e, e.getQuantity()));
        }
        invoice.setOrder(orderService.getOrder());
        invoice.setAmount(calculateTotalBill(orderService.getOrder()));
    }

    private double calculateTotalBill(Order order) {
        double offeringsTotal = 0.0;
        if (Objects.nonNull(order.getOfferings())) {
            offeringsTotal = order.getOfferings().stream().mapToDouble(Offering::getPrice).sum();
        }
        double extrasTotal = 0.0;
        if (Objects.nonNull(order.getExtras())) {
            extrasTotal = order.getExtras().stream().mapToDouble(Extra::getPrice).sum();
        }
        return ((offeringsTotal + extrasTotal) - bonus.evaluateBonus(order));
    }
}
