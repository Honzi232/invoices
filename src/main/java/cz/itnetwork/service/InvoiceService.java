package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;

import java.util.List;

public interface InvoiceService {

    /**
     * Creating an invoice
     * @param invoiceDTO Invoice details
     * @return Invoice data listing
     */
    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);

    /**
     * Retrieve a list of invoices based on filter conditions
     * @param invoiceFilter  Required filtering conditions
     * @return Filtered list of invoices
     */
    List<InvoiceDTO> getInvoices(InvoiceFilter invoiceFilter);

    /**
     * Invoice listing
     * @param invoiceId Invoice ID
     * @return Invoice detail
     */
    InvoiceDTO getInvoice(long invoiceId);

    /**
     * Removal of invoice
     * @param invoiceId Invoice ID to be removed
     */
    void removeInvoice(Long invoiceId);

    /**
     * Edit invoice
     * @param invoiceId  Invoice ID for modification
     * @param invoiceDTO New data for the invoice to be edited
     * @return Modified invoice saved to database
     */
    InvoiceDTO updateInvoice(long invoiceId, InvoiceDTO invoiceDTO);

    /**
     * List of invoices issued by a specific person.
     * @param personId Person ID for invoice listing
     * @return List of invoices issued by the person
     */
    List<InvoiceDTO> getInvoicesByBuyer(long personId);

    /**
     * List of invoices received by a specific person
     * @param personId Person ID for invoice listing
     * @return List of invoices received by person
     */
    List<InvoiceDTO> getInvoicesBySeller(long personId);

    /**
     * Extract of general invoice statistics
     * @return Listing of the amount of all invoices for the current year,
     * for the whole period and the number of invoices
     */
    InvoiceStatisticDTO invoiceStatisticDTO();

}
