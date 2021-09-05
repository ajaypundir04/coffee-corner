package com.charlene.output;


import com.charlene.model.Invoice;

/**
 * @author Ajay Singh Pundir
 * It will be used to log the output.
 */
public interface Logger {

    void printInvoice(Invoice invoice);
}
