package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceStatisticDTO;
import cz.itnetwork.dto.PersonStatisticDTO;
import cz.itnetwork.service.InvoiceService;
import cz.itnetwork.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatisticController {
    @Autowired
    private PersonService personService;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/people/statistics")
    public List<PersonStatisticDTO> getStatistics() {
        return personService.getPersonStatistics();
    }

    @GetMapping("/invoices/statistics")
    public InvoiceStatisticDTO invoiceStatisticDTO() {
        return invoiceService.invoiceStatisticDTO();
    }
}


