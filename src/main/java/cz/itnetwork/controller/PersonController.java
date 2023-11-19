/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */
package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.service.InvoiceService;
import cz.itnetwork.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/people")
    public PersonDTO addPerson(@RequestBody PersonDTO personDTO) {
        return personService.addPerson(personDTO);
    }

    @PutMapping("/people/{personId}")
    public PersonDTO updatePerson(@PathVariable Long personId, @RequestBody PersonDTO personDTO) {
       return personService.updatePerson(personId, personDTO);
    }

    @GetMapping("/people")
    public List<PersonDTO> getPeople() {
        return personService.getAllPeople();
    }

    @DeleteMapping("/people/{personId}")
    public void deletePerson(@PathVariable Long personId) {
        personService.removePerson(personId);
    }

    @GetMapping("/people/{personId}")
    public PersonDTO get(@PathVariable Long personId) {
        return personService.getPerson(personId);
    }

    @GetMapping("/identification/{identificationNumber}/purchases")
    public List<InvoiceDTO> getInvoicesByBuyer(@PathVariable String identificationNumber) {
        long personId = (personService.getPersonIdByIdentificationNumber(identificationNumber));
          return invoiceService.getInvoicesByBuyer(personId);
    }

    @GetMapping("/identification/{identificationNumber}/sales")
    public List<InvoiceDTO> getInvoicesBySeller(@PathVariable String identificationNumber) {
        long personId = (personService.getPersonIdByIdentificationNumber(identificationNumber));
        return invoiceService.getInvoicesBySeller(personId);
    }

}

