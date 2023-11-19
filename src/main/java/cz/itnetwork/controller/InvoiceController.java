package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/invoices")
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return  invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping("/invoices")
    public List<InvoiceDTO> getInvoices(InvoiceFilter invoiceFilter) {
        return invoiceService.getInvoices(invoiceFilter);
    }

    @GetMapping("/invoices/{invoiceId}")
    public InvoiceDTO getInvoice(@PathVariable Long invoiceId) {
        return invoiceService.getInvoice(invoiceId);
    }

    /**
     *Delete invoice by database Id. After successful deletion, the server responds with the code 204 (No Content).
     * @param invoiceId Invoice Id
     */
    @DeleteMapping("/invoices/{invoiceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeInvoice(@PathVariable Long invoiceId) {
        invoiceService.removeInvoice(invoiceId);
    }

    @PutMapping("/invoices/{invoiceId}")
    public InvoiceDTO updateInvoice(@PathVariable Long invoiceId, @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.updateInvoice(invoiceId, invoiceDTO);
    }

}
