package com.charlene;


import com.charlene.exception.CoffeeCornerException;
import com.charlene.entity.Extra;
import com.charlene.model.Menu;
import com.charlene.entity.Offering;
import com.charlene.model.Order;
import com.charlene.output.ConsoleLogger;
import com.charlene.output.Logger;
import com.charlene.parser.InputParser;
import com.charlene.parser.InputParserImpl;
import com.charlene.service.impl.CoffeeCornerService;
import com.charlene.service.impl.FreeBeverageBonus;
import com.charlene.service.impl.FreeExtraBonus;
import com.charlene.service.impl.OrderServiceImpl;
import com.charlene.util.InputFileReader;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author Ajay Singh Pundir
 * This is the entry point of the application
 */
public class ApplicationLauncher {

    private Menu menu;
    private Order order;
    private CoffeeCornerService coffeeCornerService;
    private InputParser parser;
    private Logger logger;

    public static void main(String[] args) {
        if (args == null || args.length < 4) {
            System.err.println("File Paths not provided.");
        } else {
            new ApplicationLauncher().launchApplication(args[0], args[1], args[2], args[3]);
        }
    }

    private void launchApplication(String menuOfferingsPath, String menuExtrasPath,
                                   String orderOfferingsPath, String orderExtrasPath) {
        try {
            parser = loadParser();
            logger = loadConsoleLogger();
            menu = loadMenu(parseOfferings(menuOfferingsPath), parseExtras(menuExtrasPath));
            order = loadOrder(parseOfferings(orderOfferingsPath), parseExtras(orderExtrasPath));
            coffeeCornerService = new CoffeeCornerService(new OrderServiceImpl(), new FreeBeverageBonus(new FreeExtraBonus()));
            logger.printInvoice(coffeeCornerService.placeOrder(order.getOfferings(), order.getExtras()));

        } catch (Exception e) {
            throw new CoffeeCornerException(e);
        }
    }

    private Order loadOrder(List<Offering> offerings, List<Extra> extras) {
        return new Order(offerings, extras);

    }

    private InputParser loadParser() {
        return new InputParserImpl();
    }

    private Logger loadConsoleLogger() {
        return new ConsoleLogger();
    }

    private CoffeeCornerService loadCoffeeCornerService() {
        return new CoffeeCornerService(new OrderServiceImpl(),
                new FreeBeverageBonus(new FreeExtraBonus()));
    }

    private Menu loadMenu(List<Offering> offerings, List<Extra> extras) {
        return new Menu(offerings, extras);
    }

    private List<Offering> parseOfferings(String filePath) throws FileNotFoundException {
        List<String> invoiceList = InputFileReader.parseFile(filePath);
        return parser.parseOfferings(invoiceList);
    }

    private List<Extra> parseExtras(String filePath) throws FileNotFoundException {
        List<String> invoiceList = InputFileReader.parseFile(filePath);
        return parser.parseExtras(invoiceList);
    }

}

