package com.charlene.logger;


import com.charlene.model.Invoice;
import com.charlene.output.ConsoleLogger;
import com.charlene.output.Logger;
import com.charlene.util.TestUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleLoggerTest {

    private Logger logger;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        logger = new ConsoleLogger();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }


    @Test
    public void printInvoiceTest() {
        Invoice invoice = new Invoice();
        invoice.setOrder(TestUtil.createOrder());
        invoice.setAmount(6.60);
        logger.printInvoice(invoice);
        Assert.assertTrue(outContent.toString().contains("Total Amount 6.60 CHF"));
    }

}
