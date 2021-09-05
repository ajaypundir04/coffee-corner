package com.charlene.output;


import com.charlene.constants.ApplicationConstants;
import com.charlene.entity.Extra;
import com.charlene.entity.Offering;
import com.charlene.model.Invoice;

import java.util.List;
import java.util.Objects;

/**
 * @author Ajay Singh Pundir
 * Printing output to the console
 */
public class ConsoleLogger implements Logger {
    @Override
    public void printInvoice(Invoice invoice) {
        if (Objects.nonNull(invoice.getOrder().getOfferings())) {
            System.out.println("Offerings");
            printOfferingsHeader(invoice.getOrder().getOfferings());
        }
        if (Objects.nonNull(invoice.getOrder().getExtras())) {
            System.out.println("Extras");
            printExtrasHeader(invoice.getOrder().getExtras());
        }

        System.out.println(String.format("Total Amount %.2f CHF", invoice.getAmount()));


    }

    private void printOfferingsHeader(List<Offering> offerings) {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println(
                String.join("",
                        String.format("%-40s", "Name "),
                        String.format("%-20s", "Size"),
                        String.format("%-10s", "Quantity"),
                        String.format("%-10s", "Price")));
        offerings
                .forEach(o -> System.out.println(
                        String.join("",
                                String.format("%-40s", o.getName()),
                                String.format("%-20s", o.getSize()),
                                String.format("%-10s", o.getQuantity()),
                                String.format("%-10s", o.getPrice() + ApplicationConstants.CURRENCY))));

        System.out.println("---------------------------------------------------------------------------------------------");

    }

    private void printExtrasHeader(List<Extra> extras) {
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println(
                String.join("",
                        String.format("%-40s", "Name "),
                        String.format("%-10s", "Quantity"),
                        String.format("%-10s", "Price")));

        extras.stream()
                .forEach(e -> System.out.println(
                        String.join("",
                                String.format("%-40s", e.getName()),
                                String.format("%-10s", e.getQuantity()),
                                String.format("%-10s", e.getPrice() + ApplicationConstants.CURRENCY))));

        System.out.println("---------------------------------------------------------------------------------------------");


    }
}
